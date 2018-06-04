package com.felipemacedo.mytuition;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.felipemacedo.mytuition.ConteudoActivity;
import com.example.felipemacedo.mytuition.R;
import com.example.felipemacedo.mytuition.conf.Configuration;
import com.example.felipemacedo.mytuition.dto.conteudo.ConteudoResultDTO;
import com.example.felipemacedo.mytuition.dto.materia.MateriaResultDTO;
import com.example.felipemacedo.mytuition.dto.save.wrapper.UsuarioMateriaSaveWrapper;
import com.example.felipemacedo.mytuition.dto.usuarioMateria.UsuarioMateriaDTO;
import com.example.felipemacedo.mytuition.dto.usuarioMateria.UsuarioMateriaMateriaDTO;
import com.example.felipemacedo.mytuition.dto.usuarioMateria.UsuarioMateriaUsuarioDTO;
import com.example.felipemacedo.mytuition.dto.wrapper.response.ConteudoResponseWrapper;
import com.example.felipemacedo.mytuition.dto.wrapper.response.MateriaResponseWrapper;
import com.example.felipemacedo.mytuition.licoes.MateriasAdapter;
import com.example.felipemacedo.mytuition.utils.RecyclerViewOnItemClickListener;
import com.example.felipemacedo.mytuition.listeners.JsonRequestListener;
import com.example.felipemacedo.mytuition.model.eclipse.Conteudo;
import com.example.felipemacedo.mytuition.model.eclipse.Materia;
import com.example.felipemacedo.mytuition.model.eclipse.UsuarioMateria;
import com.example.felipemacedo.mytuition.model.eclipse.UsuarioMateriaId;
import com.example.felipemacedo.mytuition.services.ConteudoService;
import com.example.felipemacedo.mytuition.services.MateriaService;
import com.example.felipemacedo.mytuition.services.UsuarioMateriaService;
import com.example.felipemacedo.mytuition.services.impl.ConteudoServiceImpl;
import com.example.felipemacedo.mytuition.services.impl.MateriaServiceImpl;
import com.example.felipemacedo.mytuition.services.impl.UsuarioMateriaServiceImpl;
import com.example.felipemacedo.mytuition.utils.LocalDateTimeDeserializer;
import com.example.felipemacedo.mytuition.utils.LocalTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MateriaActivity extends AppCompatActivity implements RecyclerViewOnItemClickListener {

    private static final String TAG = "MATERIAS";

    private Long disciplinaId;

    private RecyclerView mRecyclerView;
    private List<Materia> materias;
    private MateriasAdapter materiasAdapter;

    private MateriaService service;
    private ConteudoService conteudoService;
    private UsuarioMateriaService usuarioMateriaService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia);

        initComponents();
        initListeners();
    }

    private void initComponents() {

        disciplinaId = getIntent().getExtras().getLong("disciplinaId");

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_materias);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(MateriaActivity.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        service = new MateriaServiceImpl();

        materias = new ArrayList<>();

        service.findByDisciplinaId(MateriaActivity.this, disciplinaId, Configuration.usuario.getEmail(), new JsonRequestListener<JSONObject>() {
            @Override
            public void onSuccess(JSONObject response) {
                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
                        .setPrettyPrinting()
                        .create();

                MateriaResponseWrapper wrapper = gson.fromJson(String.valueOf(response), MateriaResponseWrapper.class);

                materias = convertMateriaDTOToEntity(wrapper.getMaterias());

                materiasAdapter = new MateriasAdapter(MateriaActivity.this, materias);
                materiasAdapter.setmRecyclerViewOnClickListener(MateriaActivity.this);

                mRecyclerView.setAdapter(materiasAdapter);

                materiasAdapter.notifyDataSetChanged();

            }

            private List<Materia> convertMateriaDTOToEntity(Set<MateriaResultDTO> materias) {
                List<Materia> convertedMaterias = new ArrayList<>();
                ModelMapper mapper = new ModelMapper();

                for (MateriaResultDTO m : materias) {
                    convertedMaterias.add(mapper.map(m, Materia.class));
                }

                return convertedMaterias;
            }

            @Override
            public void onError(JSONObject response) {
                Toast.makeText(MateriaActivity.this, "Não foi possível carregar as matérias", Toast.LENGTH_LONG);
                Log.e(TAG, response.toString());
            }
        });
    }

    private void initListeners() {
    }

    @Override
    public void onItemClickListener(View view, int position) {
        conteudoService = new ConteudoServiceImpl();

        conteudoService.findByMateriaId(MateriaActivity.this, materias.get(position).getId(), new JsonRequestListener<JSONObject>() {

            @Override
            public void onSuccess(JSONObject response) {
                Gson gson = new GsonBuilder()
                        .setPrettyPrinting()
                        .registerTypeAdapter(LocalTime.class, new LocalTimeDeserializer())
                        .create();

                ConteudoResponseWrapper wrapper = gson.fromJson(String.valueOf(response), ConteudoResponseWrapper.class);
                List<Conteudo> conteudos = convertConteudoDTOToEntity(wrapper.getConteudos());

                if (conteudos != null && !conteudos.isEmpty()) {

                    if (materias.get(position).getUsuarioMateria() == null) {
                        UsuarioMateriaSaveWrapper wrapperUsuarioMateria = montarWrapper(Configuration.usuario.getEmail(), materias.get(position).getId());
                        usuarioMateriaService = new UsuarioMateriaServiceImpl();
                        usuarioMateriaService.iniciarMateria(MateriaActivity.this, wrapperUsuarioMateria, new JsonRequestListener<JSONObject>() {
                            @Override
                            public void onSuccess(JSONObject response) {
                                atribuirUsuarioMateriaLocalmente(wrapperUsuarioMateria.getUsuarioMateriaDTO());
                                mostrarConteudo(position, conteudos, materias.get(position).getUsuarioMateria());
                                materiasAdapter.notifyDataSetChanged();
                            }

                            private void atribuirUsuarioMateriaLocalmente(UsuarioMateriaDTO usuarioMateriaDTO) {
                                UsuarioMateria usuarioMateria = new UsuarioMateria();
                                usuarioMateria.setInicio(usuarioMateriaDTO.getInicio());
                                usuarioMateria.setConclusao(usuarioMateriaDTO.getConclusao());

                                UsuarioMateriaId id = new UsuarioMateriaId();
                                id.setMateriaId(usuarioMateriaDTO.getMateria().getId());
                                id.setUsuarioId(usuarioMateriaDTO.getUsuario().getEmail());

                                usuarioMateria.setId(id);

                                Set<UsuarioMateria> usuarioMaterias = new HashSet<>();
                                usuarioMaterias.add(usuarioMateria);

                                materias.get(position).setUsuarioMateria(usuarioMaterias);
                            }

                            @Override
                            public void onError(JSONObject response) {
                                Toast.makeText(MateriaActivity.this, "Não foi possível iniciar a matéria", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        mostrarConteudo(position, conteudos, materias.get(position).getUsuarioMateria());
                    }

                } else {
                    Toast.makeText(MateriaActivity.this, "Não há conteúdos a serem exibidos", Toast.LENGTH_SHORT).show();
                }


                // Este trecho de código estava passando os conteúdos da matéria selecionada para a próxima activity
                //        intent.putExtra("licao", licoes.get(position));
                //
                //
                //        Long conteudosCompletados = 0L;
                //
                //
                //        if (licoes.get(position).getUsuarios().size() > 0) {
                //            conteudosCompletados = (Long) licoes.get(position).getUsuarios().values().toArray()[0];
                //        }
                //
                //        intent.putExtra("conteudos_completados", conteudosCompletados);
                //
                //        Bundle bundle = new Bundle();
                //        bundle.putString("licao", licao.getTitulo());
                //        bundle.putLong("timestamp_inicio", new Date().getTime());
                //
                //        FirebaseAnalytics fa = FirebaseAnalytics.getInstance(getContext());
                //        fa.setUserId(CurrentUser.getInstance().id);
                //        fa.logEvent("inicio_licao", bundle);

            }

            private UsuarioMateriaSaveWrapper montarWrapper(String emailUsuario, Long materiaId) {
                UsuarioMateriaDTO dto = new UsuarioMateriaDTO();
                UsuarioMateriaMateriaDTO usuarioMateriaMateriaDTO = new UsuarioMateriaMateriaDTO();
                usuarioMateriaMateriaDTO.setId(materiaId);

                UsuarioMateriaUsuarioDTO usuarioMateriaUsuarioDTO = new UsuarioMateriaUsuarioDTO();
                usuarioMateriaUsuarioDTO.setEmail(emailUsuario);

                dto.setMateria(usuarioMateriaMateriaDTO);
                dto.setUsuario(usuarioMateriaUsuarioDTO);
                dto.setInicio(LocalDateTime.now());

                UsuarioMateriaSaveWrapper wrapper = new UsuarioMateriaSaveWrapper();
                wrapper.setUsuarioMateriaDTO(dto);

                return wrapper;
            }


            private List<Conteudo> convertConteudoDTOToEntity(Set<ConteudoResultDTO> conteudos) {
                List<Conteudo> conteudosConvertidos = new ArrayList<>();
                ModelMapper mapper = new ModelMapper();

                for (ConteudoResultDTO c : conteudos) {
                    conteudosConvertidos.add(mapper.map(c, Conteudo.class));
                }

                return conteudosConvertidos;
            }

            @Override
            public void onError(JSONObject response) {

            }
        });
    }


    private void mostrarConteudo(int position, List<Conteudo> conteudos, Set<UsuarioMateria> usuarioMateria) {

        Intent intent = new Intent(this, ConteudoActivity.class);

        intent.putExtra("materia", materias.get(position));
        intent.putExtra("conteudos", (Serializable) conteudos);
        intent.putExtra("usuarioMateria", (Serializable) usuarioMateria);

        startActivity(intent);
    }
}

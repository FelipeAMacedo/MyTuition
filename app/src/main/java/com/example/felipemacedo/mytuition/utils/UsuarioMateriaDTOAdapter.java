package com.example.felipemacedo.mytuition.utils;

import com.example.felipemacedo.mytuition.dto.usuarioMateria.UsuarioMateriaDTO;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Type;

public class UsuarioMateriaDTOAdapter implements JsonDeserializer<UsuarioMateriaDTO> {


    @Override
    public UsuarioMateriaDTO deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        UsuarioMateriaDTO dto = new UsuarioMateriaDTO();


        return null;
    }
}

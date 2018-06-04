package com.felipemacedo.mytuition;

/**
 *
 * Classe contendo métodos estáticos para cálculos relativos ao Level do herói do usuário.
 *
 * @author Felipe Alves de Macedo
 *
 */
public final class Level {

    private Level() { }

    /**
     * Método responsável por calcular o level atual de acordo com a experiência informada.
     *
     * @param xp Experiência.
     * @return Level atual.
     */
    public static int calculateLevel (int xp) {
        return (int) (1 + Math.sqrt(1 + 8 * xp / 50)) / 2;
    }

    /**
     * Método responsável por calcular a quantidade de experiência necessária para alcançar o
     * próximo level.
     *
     * @param level Level atual.
     * @return Quantidade de experiência necessária para alcançar o próximo level.
     */
    public static int calculateNeededExp (int level) {
        return (level * level - level) * 50 / 2;
    }
}

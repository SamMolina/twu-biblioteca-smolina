package com.twu.biblioteca.app.ui;

import com.twu.biblioteca.app.util.BibliotecaConstants;

public class Menu {

    public static void OptionZero() {
        System.out.print(BibliotecaConstants.CHOOSE_OPTIONS.toString()
                + BibliotecaConstants.OPTION_ONE_FULL.toString()
                + BibliotecaConstants.OPTION_TWO_FULL.toString()
                + BibliotecaConstants.OPTION_THREE_FULL.toString()
                + BibliotecaConstants.OPTION_FOUR_FULL);
    }

}

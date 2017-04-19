package com.twu.biblioteca.app.ui;

import com.twu.biblioteca.app.service.BookService;
import com.twu.biblioteca.app.util.Asset;

public class Menu {

    public static void OptionZero() {
        System.out.print(Asset.CHOOSE_OPTIONS.toString()
                + Asset.OPTION_ONE_FULL.toString()
                + Asset.OPTION_TWO_FULL.toString()
                + Asset.OPTION_THREE_FULL.toString()
                + Asset.OPTION_FOUR_FULL);
    }

}

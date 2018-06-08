package com.aida.popularmovies.Model;

/**
 * Created by aida on 5/14/18.
 */

public enum Language {
    EN("English"),
    FR("French"),
    HI("Hindi"),
    JA("Japanese"),
    GE("German"),
    IT("Italian");


    String full;

    Language(String full) {
        this.full = full;
    }
}

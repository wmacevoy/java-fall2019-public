/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.wmacevoy.hello;

/**
 *
 * @author wmacevoy
 */
public class App {
    void run() {}
    private String language = "en";
    
    void setLanguage(String language) {
        if (language.equals("en") || language.equals("en")) {
            this.language = language;
        } else {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    String getMessage() {
        switch(language) {
            case "en": return "Hello WOrld!";
            case "cn": return "你好，世界!";
            
        }
        throw new IllegalStateException("inconceivable!");
    }
    
}

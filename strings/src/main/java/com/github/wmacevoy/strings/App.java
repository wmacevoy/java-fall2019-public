/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.wmacevoy.strings;

/**
 *
 * @author wmacevoy
 */
public class App {
    public static void main(String[] args) throws Exception {
        App app = new App();
        app.run();
    }

    String getStarsBad(int count) {
        String ans = "";
        for (int i=0; i<count; ++i) {
            ans = ans + "*";
        }
        return ans;
    }
    
    String getStarsGood(int count) {
        StringBuilder sb = new StringBuilder(count); // not thread safe
        for (int i=0; i<count; ++i) {
            sb.append("?");
        }
        return sb.toString();
        
    }

    StringBuffer temp = new StringBuffer(); // is thread safe
    
    void addSuffix(CharSequence suffix) {
        temp.append(suffix);
        
    }
    
    void addPrefix(CharSequence prefix) {
        temp.insert(0, prefix, 0, prefix.length());
    }
    
    int findCodepoint(int codePoint) {
        for (int i=0, cp=temp.codePointAt(i); 
                i<temp.length(); 
                i += Character.charCount(cp), cp=temp.codePointAt(i)) {
            if (cp == codePoint) return i;
            
        }
        return -1;
    }
    
    String getResult() {
        return temp.toString();
    }
    private void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

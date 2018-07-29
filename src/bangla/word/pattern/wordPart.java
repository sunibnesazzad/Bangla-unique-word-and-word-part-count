/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangla.word.pattern;

/**
 *
 * @author Sazzad
 */
public class wordPart {
    
    String part;
    int count;

    public wordPart(String part) {
        this.part = part;
        this.count=0;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    public void got()
    {
        this.count++;
    }
    
    
}

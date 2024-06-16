/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

import abstracto.Instruccion;
import excepciones.Errores;
import simbolo.Arbol;
import simbolo.Tipo;
import simbolo.TipoDato;
import simbolo.tablaSimbolos;

/**
 *
 * @author Diego
 */
public class Logicos extends Instruccion {
    private Instruccion cond1;
    private Instruccion cond2;
    private OperadoresLogicos logico;
    private Instruccion operadorUnico;

    public Logicos(OperadoresLogicos logico, Instruccion operadorUnico, int linea, int col) {
        super(new Tipo(TipoDato.BOOLEANO), linea, col);
        this.logico = logico;
        this.operadorUnico = operadorUnico;
    }
    
    

    public Logicos(Instruccion cond1, Instruccion cond2, OperadoresLogicos logico, int linea, int col) {
        super(new Tipo(TipoDato.BOOLEANO), linea, col);
        this.cond1 = cond1;
        this.cond2 = cond2;
        this.logico = logico;
    }
    
    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var condIzq = this.cond1.interpretar(arbol, tabla);
        if (condIzq instanceof Errores) {
            return condIzq;
        }

        var condDer = this.cond2.interpretar(arbol, tabla);
        if (condDer instanceof Errores) {
            return condDer;
        }
        var unico = this.operadorUnico.interpretar(arbol, tabla);
        if(unico instanceof Errores){
            return unico;
        }
        
        return switch(logico){
            case OR ->
                this.loO(condIzq, condDer);
            case AND->
                this.loY(condIzq, condDer);
            case XOR ->
                this.loXor(condIzq, condDer);
            case NOT ->
                this.loN(condIzq);
            default -> 
                new Errores("SEMANTICO", "Operador Logico Invalido", this.linea, this.col);
        };
    }
    
    public Object loO(Object comp1, Object comp2){
        var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();
        
        return switch(comparando1){
            case BOOLEANO ->
                switch (comparando2){
                    case BOOLEANO->
                        ((boolean)comp1) || ((boolean)comp2);
                    default -> 
                        new Errores("SEMANTICO", "Operador Logico Invalido", this.linea, this.col);
                };
            default ->
                new Errores("SEMANTICO", "Operador Logico Invalido", this.linea, this.col);
        };
    }
    
    public Object loY(Object comp1, Object comp2){
        var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();
        
        return switch(comparando1){
            case BOOLEANO->
                switch(comparando2){
                    case BOOLEANO->
                        (boolean) comp1 && (boolean) comp2;
                    default ->
                        new Errores("SEMANTICO", "Operador Logico Invalido", this.linea, this.col);
                };
            default ->
                new Errores("SEMANTICO", "Operador Logico Invalido", this.linea, this.col);
        };
    }
    
    public Object loXor(Object comp1, Object comp2){
        var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();
        
        return switch(comparando1){
            case BOOLEANO->
                switch(comparando2){
                    case BOOLEANO->
                        (boolean) comp1 ^ (boolean) comp2;
                    default ->
                        new Errores("SEMANTICO", "Operador Logico Invalido", this.linea, this.col);
                };
            default ->
                new Errores("SEMANTICO", "Operador Logico Invalido", this.linea, this.col);
        };
    }
    
    public Object loN(Object comp1){
        var comparando1 = this.cond1.tipo.getTipo();
        
        return switch(comparando1){
            case ENTERO->
                !((boolean) comp1);
            default ->
                new Errores("SEMANTICO", "Operador Logico Invalido", this.linea, this.col);
        };
    }

}

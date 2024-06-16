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
public class Relacionales extends Instruccion{
    private Instruccion cond1;
    private Instruccion cond2;
    private OperadoresRelacionales relacional;
    
    public Relacionales(Instruccion cond1, Instruccion cond2, OperadoresRelacionales relacional, int linea, int col) {
        super(new Tipo(TipoDato.BOOLEANO), linea, col);
        this.cond1 = cond1;
        this.cond2 = cond2;
        this.relacional = relacional;
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

        return switch (relacional) {
            case IGUAL ->
                this.igual(condIzq, condDer);
            case NOIGUAL ->
                this.noIgual(condIzq, condDer);
            case MENOR ->
                this.menor(condIzq, condDer);
            case MAYOR ->
                this.mayor(condIzq, condDer);
            case MENORQ ->
                this.menorq(condIzq, condDer);
            case MAYORQ ->
                this.mayorq(condIzq, condDer);
            default ->
                new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
        };
    }
    
    public Object igual(Object comp1, Object comp2) {
        var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();
        
        return switch (comparando1) {
            case ENTERO ->
                switch (comparando2) {
                    case ENTERO ->
                        (int) comp1 == (int) comp2;
                    case DECIMAL ->
                        (int) comp1 == (double) comp2;
                    case CARACTER ->
                        (int) comp1 == (int) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case DECIMAL ->
                switch (comparando2) {
                    case ENTERO ->
                        (double) comp1 == (int) comp2;
                    case DECIMAL ->
                        (double) comp1 == (double) comp2;
                    case CARACTER ->
                        (int) comp1 == (int) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case CADENA ->
                switch (comparando2) {
                    case CADENA ->
                        comp1.toString().equalsIgnoreCase(comp2.toString());
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case BOOLEANO ->
                switch(comparando2){
                    case BOOLEANO ->
                        (int) comp1 == (int) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case CARACTER ->
                switch(comparando2){
                    case ENTERO -> 
                        (int) comp1 == (int) comp2;
                    case DECIMAL ->
                        (int) comp1 == (double) comp2;
                    case CARACTER ->
                        (int) comp1 == (int) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            
            default ->
                new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
        };
    }
    
    public Object noIgual(Object comp1, Object comp2){
        var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();
        
        return switch (comparando1) {
            case ENTERO ->
                switch(comparando2){
                    case ENTERO ->
                        (int) comp1 != (int) comp2;
                    case DECIMAL ->
                        (int) comp1 != (double) comp2;
                    case CARACTER ->
                        (int) comp1 != (int) comp2;
                    default ->
                         new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case DECIMAL ->
                switch(comparando2){
                    case ENTERO ->
                        (double) comp1 != (int) comp2;
                    case DECIMAL ->
                        (double) comp1 != (double) comp2;
                    case CARACTER ->
                        (double) comp1 != (int) comp2;
                    default ->
                         new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case BOOLEANO ->
                switch(comparando2){
                    case BOOLEANO ->
                        (int) comp1 != (int) comp2;
                    default ->
                         new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case CARACTER ->
                switch(comparando2){
                    case ENTERO ->
                        (int) comp1 != (int) comp2;
                    case DECIMAL ->
                        (int) comp1 != (double) comp2;
                    case CARACTER ->
                        (int) comp1 != (int) comp2;
                    default ->
                         new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case CADENA ->
                switch(comparando2){
                    case CADENA ->
                        !comp1.toString().equalsIgnoreCase(comp2.toString());
                    default ->
                         new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            default -> 
                new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
        };
        
    }
    
    public Object menor(Object comp1, Object comp2){
        var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();
        
        return switch (comparando1) {
            case ENTERO ->
                switch(comparando2){
                    case ENTERO ->
                        (int) comp1 < (int) comp2;
                    case DECIMAL ->
                        (int) comp1 < (double) comp2;
                    case CARACTER ->
                        (int) comp1 < (int) comp2;
                    default ->
                         new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case DECIMAL ->
                switch(comparando2){
                    case ENTERO ->
                        (double) comp1 < (int) comp2;
                    case DECIMAL ->
                        (double) comp1 < (double) comp2;
                    case CARACTER ->
                        (int) comp1 < (int) comp2;
                    default ->
                         new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case BOOLEANO ->
                switch(comparando2){
                    case BOOLEANO ->
                        (double) comp1 < (int) comp2;
                    default ->
                         new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case CARACTER ->
                switch(comparando2){
                    case ENTERO ->
                        (double) comp1 < (int) comp2;
                    case DECIMAL ->
                        (double) comp1 < (double) comp2;
                    case CARACTER ->
                        (int) comp1 < (int) comp2;
                    default ->
                         new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            default ->
                new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
        };
    }
    
    public Object mayor(Object comp1, Object comp2){
        var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();
        
        return switch (comparando1) {
            case ENTERO ->
                switch(comparando2){
                    case ENTERO ->
                        (int) comp1 > (int) comp2;
                    case DECIMAL ->
                        (int) comp1 > (double) comp2;
                    case CARACTER ->
                        (int) comp1 > (int) comp2;
                    default ->
                         new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case DECIMAL ->
                switch(comparando2){
                    case ENTERO ->
                        (double) comp1 > (int) comp2;
                    case DECIMAL ->
                        (double) comp1 > (double) comp2;
                    case CARACTER ->
                        (double) comp1 > (int) comp2;
                    default ->
                         new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case BOOLEANO ->
                switch(comparando2){
                    case ENTERO ->
                        (int) comp1 > (int) comp2;
                    case DECIMAL ->
                        (int) comp1 > (double) comp2;
                    case CARACTER ->
                        (int) comp1 > (int) comp2;
                    default ->
                         new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case CARACTER ->
                switch(comparando2){
                    case ENTERO ->
                        (int) comp1 > (int) comp2;
                    case DECIMAL ->
                        (int) comp1 > (double) comp2;
                    case CARACTER ->
                        (int) comp1 > (int) comp2;
                    default ->
                         new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            default ->
                new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
        };
    }
    
    public Object menorq(Object comp1, Object comp2){
        var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();
        
        return switch (comparando1) {
            case ENTERO ->
                switch(comparando2){
                    case ENTERO ->
                        (int) comp1 <= (int) comp2;
                    case DECIMAL ->
                        (int) comp1 <= (double) comp2;
                    case CARACTER ->
                        (int) comp1 <= (int) comp2;
                    default ->
                         new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case DECIMAL ->
                switch(comparando2){
                    case ENTERO ->
                        (double) comp1 <= (int) comp2;
                    case DECIMAL ->
                        (double) comp1 <= (double) comp2;
                    case CARACTER ->
                        (double) comp1 <= (int) comp2;
                    default ->
                         new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case BOOLEANO ->
                switch(comparando2){
                    case BOOLEANO ->
                        (int) comp1 <= (int) comp2;
                    default ->
                         new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case CARACTER ->
                switch(comparando2){
                    case ENTERO ->
                        (int) comp1 <= (int) comp2;
                    case DECIMAL ->
                        (int) comp1 <= (double) comp2;
                    case CARACTER ->
                        (int) comp1 <= (int) comp2;
                    default ->
                         new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            default ->
                new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
        };
    }
    
    public Object mayorq(Object comp1, Object comp2){
        var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();
        
        return switch (comparando1) {
            case ENTERO ->
                switch(comparando2){
                    case ENTERO ->
                        (int) comp1 >= (int) comp2;
                    case DECIMAL ->
                        (int) comp1 >= (double) comp2;
                    case CARACTER ->
                        (int) comp1 >= (int) comp2;
                    default ->
                         new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case DECIMAL ->
                switch(comparando2){
                    case ENTERO ->
                        (double) comp1 >= (int) comp2;
                    case DECIMAL ->
                        (double) comp1 >= (double) comp2;
                    case CARACTER ->
                        (int) comp1 >= (int) comp2;
                    default ->
                         new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case BOOLEANO ->
                switch(comparando2){
                    case BOOLEANO ->
                        (int) comp1 >= (int) comp2;
                    default ->
                         new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case CARACTER ->
                switch(comparando2){
                    case ENTERO ->
                        (int) comp1 >= (int) comp2;
                    case DECIMAL ->
                        (int) comp1 >= (double) comp2;
                    case CARACTER ->
                        (int) comp1 >= (int) comp2;
                    default ->
                         new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            default ->
                new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
        };
    }
}

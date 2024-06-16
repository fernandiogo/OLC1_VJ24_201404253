/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

import abstracto.Instruccion;
import excepciones.Errores;
import simbolo.*;

/**
 *
 * @author Diego
 */
public class Aritmeticas extends Instruccion {
    private Instruccion operando1;
    private Instruccion operando2;
    private OperadoresAritmeticos operacion;
    private Instruccion operadorUnico;

    public Aritmeticas(Instruccion operadorUnico, OperadoresAritmeticos operacion, int linea, int col) {
        super(new Tipo(TipoDato.ENTERO), linea, col);
        this.operacion = operacion;
        this.operadorUnico = operadorUnico;
    }

    public Aritmeticas(Instruccion operando1, Instruccion operando2, OperadoresAritmeticos operacion, int linea, int col) {
        super(new Tipo(TipoDato.ENTERO), linea, col);
        this.operando1 = operando1;
        this.operando2 = operando2;
        this.operacion = operacion;
    }
    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Object opIzq = null;
        Object opDer = null;
        Object Unico = null;
        if(this.operadorUnico != null){
            Unico = this.operadorUnico.interpretar(arbol, tabla);
            if(Unico instanceof Errores){
                return Unico;
            }
        }else {
            opIzq = this.operando1.interpretar(arbol, tabla);
            if(opIzq instanceof Errores){
                return opIzq;
            }
            opDer = this.operando2.interpretar(arbol, tabla);
            if(opDer instanceof Errores){
                return opDer;
            }
        }
        
        return switch (operacion){
            case SUMA -> 
                this.suma(opIzq, opDer);
            case NEGACION -> 
                this.negacion(Unico);
            case RESTA -> 
                this.resta(opIzq, opDer);
            case MULTIPLICACION -> 
                this.multiplicacion(opIzq, opDer);
            case DIVISION -> 
                this.division(opIzq, opDer);
            case MODULO -> 
                this.modulo(opIzq, opDer);
            case POTENCIA ->
                this.potencia(opIzq, opDer);
            default ->
                new Errores("SEMANTICO", "Operador invalido", this.linea, this.col);
        };
    }
    
     public Object suma(Object op1, Object op2) {
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();
        
        switch(tipo1){
            case ENTERO ->{
                switch(tipo2){
                    case ENTERO ->{
                        this.tipo.setTipo(TipoDato.ENTERO);
                        return (int) op1 + (int) op2;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (int) op1 + (double) op2;
                    }
                    case CARACTER ->{
                        this.tipo.setTipo(TipoDato.ENTERO);
                        return (int) op1 + (int)op2;
                    }
                    case CADENA ->{
                        this.tipo.setTipo(TipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    default ->{
                        return new Errores("SEMANTICO", "Suma erronea de entero con otro operador diferente", this.linea, this.col);
                    }
                }
            }
            case DECIMAL ->{
                switch(tipo2){
                    case ENTERO ->{
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double)((double) op1 + (int) op2);
                    }
                    case DECIMAL ->{
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double) op1 + (double) op2;
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double) op1 + (int) op2;
                    }
                    case CADENA -> {
                        this.tipo.setTipo(TipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea de decimal con otro operador diferente", this.linea, this.col);
                    }
                }
            }
            case BOOLEANO -> {
                switch(tipo2){
                    case CADENA -> {
                        this.tipo.setTipo(TipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    default ->{
                        return new Errores("SEMANTICO", "Suma erronea de booleano con otro operador diferente", this.linea, this.col);
                    }
                }
            }
            case CARACTER ->{
                switch(tipo2){
                    case ENTERO -> {
                        this.tipo.setTipo(TipoDato.ENTERO);
                        return (int) op1 + (int) op2;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (int) op1 + (double) op2;
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(TipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    case CADENA -> {
                        this.tipo.setTipo(TipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    default ->{
                        return new Errores("SEMANTICO", "Suma erronea de caracter con otro operador diferente", this.linea, this.col);
                    }
                }
            }
            case CADENA -> {
                switch (tipo2){
                    case ENTERO ->{
                        this.tipo.setTipo(TipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    case DECIMAL ->{
                        this.tipo.setTipo(TipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    case BOOLEANO ->{
                        this.tipo.setTipo(TipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    case CARACTER ->{
                        this.tipo.setTipo(TipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    case CADENA ->{
                        this.tipo.setTipo(TipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    default ->{
                        return new Errores("SEMANTICO", "Suma erronea de cadena con otro operador diferente", this.linea, this.col);
                    }
                }
            }
            default ->{
                return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
            }
        }
     }
     
     public Object resta(Object op1, Object op2){
         var tipo1 = this.operando1.tipo.getTipo();
         var tipo2 = this.operando2.tipo.getTipo();
         
         switch(tipo1){
             case ENTERO ->{
                 switch(tipo2){
                     case ENTERO ->{
                        this.tipo.setTipo(TipoDato.ENTERO);
                        return (int) op1 - (int) op2;
                     }
                     case DECIMAL ->{
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (int) op1 - (double) op2;
                     }
                     case CARACTER ->{
                        this.tipo.setTipo(TipoDato.ENTERO);
                        return (int) op1 - (int) op2;
                     }
                     default ->{
                          return new Errores("SEMANTICO", "Resta erronea de entero con otro operador diferente", this.linea, this.col);
                     }
                 }
             }
             case DECIMAL ->{
                 switch(tipo2){
                    case ENTERO->{
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double) op1 - (int) op2;
                    }
                    case DECIMAL->{
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double) op1 - (double) op2;
                    }
                    case CARACTER->{
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double) op1 - (int) op2;
                    }
                    default->{
                        return new Errores("SEMANTICO", "Resta erronea de decimal con otro operador diferente", this.linea, this.col);
                    }
                 }
             }
             case CARACTER ->{
                 switch(tipo2){
                    case ENTERO->{
                        this.tipo.setTipo(TipoDato.ENTERO);
                        return (int) op1 - (int) op2;
                    }
                    case DECIMAL->{
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (int) op1 - (double) op2;
                    }
                    default ->{
                        return new Errores("SEMANTICO", "Resta erronea de caracter con otro operador diferente", this.linea, this.col);
                    }
                 }
             }
             default ->{
                 return new Errores("SEMANTICO", "Resta erronea", this.linea, this.col);
             }
         }
     }
     
     public Object multiplicacion(Object op1, Object op2){
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();
        
        switch(tipo1){
            case ENTERO ->{
                switch(tipo2){
                    case ENTERO ->{
                        this.tipo.setTipo(TipoDato.ENTERO);
                        return (int) op1 * (int) op2;
                    }
                    case DECIMAL ->{
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (int) op1 * (double) op2;
                    }
                    case CARACTER ->{
                        this.tipo.setTipo(TipoDato.ENTERO);
                        return (int) op1 * (int) op2;
                    }
                    default ->{
                        return new Errores("SEMANTICO", "Multiplicación erronea de entero con otro operador", this.linea, this.col);
                    }
                }
            }
            case DECIMAL ->{
                switch(tipo2){
                    case ENTERO ->{
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double) op1 * (int) op2;
                    }
                    case DECIMAL ->{
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double) op1 *(double) op2;
                    }
                    case CARACTER ->{
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double) op1 * (int) op2;
                    }
                    default ->{
                         return new Errores("SEMANTICO", "Multiplicación erronea de decimal con otro operador", this.linea, this.col);
                    }
                }
            }
            case CARACTER ->{
               switch(tipo2){
                   case ENTERO ->{
                       this.tipo.setTipo(TipoDato.ENTERO);
                       return (int) op1 * (int) op2;
                   }
                   case DECIMAL ->{
                       this.tipo.setTipo(TipoDato.DECIMAL);
                       return (int) op1 * (double) op2;
                   }
                   default ->{
                       return new Errores("SEMANTICO", "Multiplicación erronea de caracter con otro operador", this.linea, this.col);
                   }
               }
            }
            default ->{
                return new Errores("SEMANTICO", "Multiplicación erronea", this.linea, this.col);
            }
        }
     }
     
     public Object division(Object op1, Object op2){
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();
        
        /*
        if((int) op1 == 0 || (double) op2 == 0.0){
        return new Errores("SEMANTICO", "Division de con cero", this.linea, this.col);
        } 
        */
        switch(tipo1){
            case ENTERO ->{
                switch(tipo2){
                    case ENTERO ->{
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double)((int) op1 / (int) op2);
                    }
                    case DECIMAL ->{
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double)((int) op1 / (double) op2);
                    }
                    case CARACTER ->{
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double)((int) op1 / (int) op2);
                    }
                    default ->{
                        return new Errores("SEMANTICO", "Division erronea de entero con otro operador", this.linea, this.col);
                    }
                }
            }
            case DECIMAL ->{
                switch(tipo2){
                    case ENTERO ->{
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double)((double) op1 / (int) op2);
                    }
                    case DECIMAL ->{
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double)((double) op1 / (double) op2);
                    }
                    case CARACTER ->{
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double)((double) op1 / (int) op2);
                    }
                    default ->{
                        return new Errores("SEMANTICO", "Division erronea de decimal con otro operador", this.linea, this.col);
                    }
                }
            }
            case CARACTER ->{
                switch(tipo2){
                    case ENTERO ->{
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double)((int) op1 / (int) op2);
                    }
                    case DECIMAL ->{
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double)((int) op1 / (double) op2);
                    }
                    default ->{
                        return new Errores("SEMANTICO", "Division erronea de caracter con otro operador", this.linea, this.col);
                    }
                }
            }
            default ->{
                return new Errores("SEMANTICO", "Division erronea", this.linea, this.col);
            }
        }
     }
     
     public Object modulo(Object op1, Object op2){
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();
        
        switch(tipo1){
            case ENTERO ->{
                switch (tipo2){
                    case ENTERO ->{
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (int) op1 % (int) op2;
                    }
                    case DECIMAL ->{
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (int) op1 % (double) op2;
                    }
                    default ->{
                        return new Errores("SEMANTICO", "Modulo erroneo de entero con otro operador", this.linea, this.col);
                    }
                }
            }
            case DECIMAL ->{
                switch (tipo2){
                    case ENTERO ->{
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double) op1 % (int) op2;
                    }
                    case DECIMAL ->{
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double) op1 % (double) op2;
                    }
                    default ->{
                        return new Errores("SEMANTICO", "Modulo erroneo de decimal con otro operador", this.linea, this.col);
                    }
                }
            }
            default ->{
                return new Errores("Semantico", "Modulo Erroneo", this.linea, this.col);
            }
        }
     }
     
     public Object potencia(Object op1, Object op2){
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();
        
        switch(tipo1){
            case ENTERO ->{
                switch(tipo2){
                    case ENTERO ->{
                        this.tipo.setTipo(TipoDato.ENTERO);
                        return Math.pow((int)op1, (int)op2);
                    }
                    case DECIMAL ->{
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return Math.pow((int)op1, (double)op2);
                    }
                    default ->{
                        return new Errores("SEMANTICO", "Potencia erronea de entero con otro operador", this.linea, this.col);
                    }
                }
            }
            case DECIMAL ->{
                switch(tipo2){
                    case ENTERO ->{
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return Math.pow((double)op1, (int)op2);
                    }
                    case DECIMAL ->{
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return Math.pow((double)op1, (double)op2);
                    }
                    default ->{
                        return new Errores("SEMANTICO", "Potencia erronea de decimal con otro operador", this.linea, this.col);
                    }
                }
            }
            default ->{
                return new Errores("SEMANTICO", "Potencia erronea", this.linea, this.col);
            }
        }
     }
     
     public Object negacion(Object op1){
        var opU = this.operadorUnico.tipo.getTipo();
         
        switch (opU) {
            case ENTERO -> {
                this.tipo.setTipo(TipoDato.ENTERO);
                return (int) op1 * -1;
            }
            case DECIMAL -> {
                this.tipo.setTipo(TipoDato.DECIMAL);
                return (double) op1 * -1;
            }
            default -> {
                return new Errores("SEMANTICO", "Negacion erronea", this.linea, this.col);
            }
        }
     }
}



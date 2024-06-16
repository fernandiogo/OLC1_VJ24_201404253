/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

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
public class AsignacionVar extends Instruccion{
    private String id;
    private Instruccion exp;

    public AsignacionVar(String id, Instruccion exp, int linea, int col) {
        super(new Tipo(TipoDato.VOID), linea, col);
        this.id = id;
        this.exp = exp;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var variable = tabla.getVariable(id);
        if(variable==null){
            return new Errores("SEMANTICO", "La variable no existe para asignarle valor", this.linea, this.col);
        }
        /*
        if(!variable.mutabilidad){
             return new Errores("SEMANTICO", "La variable no es mutable", this.linea, this.col);
        }
        */  
        var newValor = this.exp.interpretar(arbol, tabla);
        if(newValor instanceof Errores){
            return newValor;
        }
        
        if(variable.getTipo().getTipo() != this.exp.tipo.getTipo()){
            return new Errores("SEMANTICO", "Tipos diferentes de variables para asignar nuevo valor", this.linea, this.col);
        }
        //this.tipo.setTipo(variable.getTipo().getTipo());
        variable.setValor(newValor);
        return null;
    }
    
    
}

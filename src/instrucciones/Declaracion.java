/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import excepciones.Errores;
import simbolo.Arbol;
import simbolo.Simbolo;
import simbolo.Tipo;
import simbolo.tablaSimbolos;

/**
 *
 * @author Diego
 */
public class Declaracion extends Instruccion{
    public String identificador;
    public Instruccion valor;

    public Declaracion(String identificador, Instruccion valor, Tipo tipo, int linea, int col) {
        super(tipo, linea, col);
        this.identificador = identificador;
        this.valor = valor;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var valorInterpretado = this.valor.interpretar(arbol, tabla);
        if(valorInterpretado instanceof Errores){
            return valorInterpretado;
        }
        
        if(this.valor.tipo.getTipo() != this.tipo.getTipo()){
            return new Errores("SEMANTICO", "No son del mismo tipo", this.linea, this.col);
        }
        
        Simbolo s = new Simbolo(this.tipo, this.identificador, valorInterpretado);
        
        boolean creacion = tabla.setVariable(s);
        if(!creacion){
            return new Errores("SEMANTICO", "Ya existe la variable", this.linea, this.col);
        }
        return null;
    }
    
    
}

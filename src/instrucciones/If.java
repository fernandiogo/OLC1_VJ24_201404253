/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import excepciones.Errores;
import java.util.LinkedList;
import simbolo.*;

/**
 *
 * @author Diego
 */
public class If extends Instruccion{
    private Instruccion condicion;
    private LinkedList<Instruccion> instrucciones;

    public If(Instruccion condicion, LinkedList<Instruccion> instrucciones, int linea, int col) {
        super(new Tipo(TipoDato.VOID), linea, col);
        this.condicion = condicion;
        this.instrucciones = instrucciones;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var cond = this.condicion.interpretar(arbol, tabla);
        if(cond instanceof Errores){
            return cond;
        }
        
        if(this.condicion.tipo.getTipo() != TipoDato.BOOLEANO){
            return new Errores("SEMANTICO", "Expresion invalida", this.linea, this.col);
        }
        
        var newTabla = new tablaSimbolos(tabla);
        if((boolean) cond){
            for(var i: this.instrucciones){
                var resultado = i.interpretar(arbol, newTabla);
                /*
                if(resultado instanceof Errores){
                    arbol.errores.add((Errores) resultado);
                }
                */
            }
        }
        return null;
    }
    
    
}

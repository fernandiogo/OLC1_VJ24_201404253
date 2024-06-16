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
public class For extends Instruccion{
    private Instruccion asignacion;
    private Instruccion condicion;
    private Instruccion actualizacion;
    private LinkedList<Instruccion> instrucciones;

    public For(Instruccion asignacion, Instruccion condicion, Instruccion actualizacion, LinkedList<Instruccion> instrucciones, int linea, int col) {
        super(new Tipo(TipoDato.VOID), linea, col);
        this.asignacion = asignacion;
        this.condicion = condicion;
        this.actualizacion = actualizacion;
        this.instrucciones = instrucciones;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var newTabla = new tablaSimbolos(tabla);
        //newTabla.setNombre(tabla.getNombre()+"FOR");
        
        var res1 = this.asignacion.interpretar(arbol, newTabla);
        if(res1 instanceof Errores){
            return res1;
        }
        
        var cond = this.condicion.interpretar(arbol, newTabla);
        if(cond instanceof Errores){
            return cond;
        }
        if(this.condicion.tipo.getTipo() != TipoDato.BOOLEANO){
            return new Errores("SEMANTICO", "La condicion debe ser BOOL", this.linea, this.col);
        }
        
        while((boolean)this.condicion.interpretar(arbol, newTabla)){
            var newTabla2 = new tablaSimbolos(newTabla);
            for(var i: this.instrucciones){
                var resIns = i.interpretar(arbol, newTabla2);
            }
            
            var act = this.actualizacion.interpretar(arbol, newTabla);
            if(act instanceof Errores){
                return act;
            }
        }
        
        return null;
    }
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author Diego
 */
public class Errores {
    private String tipo;
    private String descripcion;
    private int linea;
    private int columna;

    public Errores(String tipo, String descripcion, int linea, int col) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.linea = linea;
        this.columna = columna;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getLinea() {
        return linea;
    }

    public int getColumna() {
        return columna;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    @Override
    public String toString() {
        return "Errores{" + "tipo=" + tipo + ", descripcion=" + descripcion + ", linea=" + linea + ", columna=" + columna + '}';
    }
    
    
}

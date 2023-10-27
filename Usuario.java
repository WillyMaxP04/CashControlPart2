package com.mycompany.cashcontroljava;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable{
    //Atributos
    private String nombre;
    private String correo;
    private String clave;
    
    //Variables
    private final List<Cuenta> Ingresos;
    private final List<Cuenta> Gastos;
    CalculadoraYOperaciones calcu = new CalculadoraYOperaciones();
    PlanificadorDeAhorro Ahorros = new PlanificadorDeAhorro();
    String reporte="";
    
    //Constructor
    public Usuario(String nombre, String correo, String clave) {
        this.nombre = nombre;
        this.correo = correo;
        this.clave = clave;
        this.Ingresos = new ArrayList<>();
        this.Gastos = new ArrayList<>();
    }
    
    //Getter y setter de cada variable
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getReporte() {
        return reporte;
    }

    public void setReporte(String reporte) {
        this.reporte = reporte;
    }
    
    //Metodos especificos de la clase usuario
    public void AñadirIngreso(String Categoria, String MedioDePago, Boolean TipoDeCiclo, Float Magnitud, int NumCuotas, int NumRegistro,Boolean Tipo){
        Ingreso nuevoI = new Ingreso(Categoria,MedioDePago,TipoDeCiclo,Magnitud,NumCuotas,NumRegistro, Tipo);
        Ingresos.add(nuevoI);
    }
    
    public void AñadirGasto(String Categoria, String MedioDePago, Boolean TipoDeCiclo, Float Magnitud, int NumCuotas, int NumRegistro,String entidad){
        Egreso nuevoG = new Egreso(Categoria, MedioDePago, TipoDeCiclo, Magnitud, NumCuotas, NumRegistro,entidad);
        
    }
    
    public String LecturaIngresos(){
        reporte="";
        
        if(calcu.contI!=0){
            for(int i=0;i<calcu.contI;i++){
                reporte+=" "+(i+1)+". "+Ingresos.get(i).ImprimirInfo()+"\n";
            }
        }
        
        reporte+="Suma Total: "+calcu.SumaIngresos(0);
        return reporte;
    }
    
    
    public String LecturaGastos(){
        reporte="";
        
        if(calcu.contG!=0){
            for(int i=0;i<calcu.contG;i++){
                reporte+=" "+(i+1)+". "+Gastos.get(i).ImprimirInfo()+"\n";
            }
        }
        
        reporte+="Suma Total: "+calcu.SumaGastos(0);
        return reporte;
    }
    
    public void CrearMeta(){
        double dif = calcu.DiferenciaDeValores(calcu.totalI, calcu.totalG);
        Ahorros.CreacionDeMetaAhorro(dif);
    }
}

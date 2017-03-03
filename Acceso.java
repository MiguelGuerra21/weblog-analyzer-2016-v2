import java.util.Scanner;
public class Acceso
{
    private int a�o;
    private int mes;
    private int dia;
    private int hora;
    private int minutos;
    private Scanner sc;

    public Acceso(String fechaAcceso)
    {
        String fechaIntroducida = fechaAcceso;
        sc = new Scanner(fechaIntroducida);
        if(sc.hasNext()){
            String[] fechaAGuardar = sc.nextLine().split(" ");
            a�o = Integer.parseInt(fechaAGuardar[0]);
            mes = Integer.parseInt(fechaAGuardar[1]);
            dia = Integer.parseInt(fechaAGuardar[2]);
            hora = Integer.parseInt(fechaAGuardar[3]);
            minutos = Integer.parseInt(fechaAGuardar[4]);
        }
    }

    public int getAno() 
    {
        return a�o;
    }

    public int getMes()
    {
        return mes;
    }

    public int getDia()
    {
        return dia;
    }

    public int getHora()
    {
        return hora;
    }

    public int getMinutos()
    {
        return minutos;
    }
}
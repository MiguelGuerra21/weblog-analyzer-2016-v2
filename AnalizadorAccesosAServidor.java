import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class AnalizadorAccesosAServidor
{
    private ArrayList<Acceso> accesos;
    private HashMap<String , Integer> paginas;

    public AnalizadorAccesosAServidor() 
    {
        accesos = new ArrayList<>();
        paginas = new HashMap<>();
    }

    public void analizarArchivoDeLog(String archivo)
    {
        accesos.clear();
        File archivoALeer = new File(archivo);
        try {
            Scanner sc = new Scanner(archivoALeer);
            while (sc.hasNextLine()) {
                String lineaLeida = sc.nextLine();

                Acceso accesoActual = new Acceso(lineaLeida);

                accesos.add(accesoActual);
            }
            sc.close();
        }
        catch (Exception e) {
            System.out.println("Ocurrio algun error al leer el archivo.");
        }
    }

    public int obtenerHoraMasAccesos() 
    {
        int valorADevolver = -1;

        if (!accesos.isEmpty()) {
            int[] accesosPorHora = new int[24];

            for (Acceso accesoActual : accesos) {
                int horaAccesoActual = accesoActual.getHora();
                accesosPorHora[horaAccesoActual] = accesosPorHora[horaAccesoActual] + 1;
            }

            int numeroDeAccesosMasAlto = accesosPorHora[0];
            int horaDeAccesosMasAlto = 0;
            for (int i = 0; i < accesosPorHora.length; i++) {
                if (accesosPorHora[i] >= numeroDeAccesosMasAlto) {
                    numeroDeAccesosMasAlto = accesosPorHora[i];
                    horaDeAccesosMasAlto = i;
                }
            }

            valorADevolver = horaDeAccesosMasAlto;                      
        }

        return valorADevolver;
    }

    public String paginaWebMasSolicitada() 
    {
        paginas.clear();
        String cadenaAMostrar = null;
        int paginaMasRepetida = -1;
        for(Acceso accesoActual : accesos){
            if(!paginas.containsKey(accesoActual.getPagina())){
                paginas.put(accesoActual.getPagina() , 0);
            }
        }
        for(Acceso acceso :accesos){
            paginas.put(acceso.getPagina() , paginas.get(acceso.getPagina()) + 1);
        }
        for(String pagina : paginas.keySet()){
            if(pagina != null && paginas.get(pagina) > paginaMasRepetida){
                paginaMasRepetida = paginas.get(pagina);
                cadenaAMostrar = pagina;
            }
        }
        return cadenaAMostrar;
    }

    public String clienteConMasAccesosExitosos()
    {
        return "";
    }

}

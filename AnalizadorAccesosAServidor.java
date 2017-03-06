import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class AnalizadorAccesosAServidor
{
    private ArrayList<Acceso> accesos;
    private HashMap<String , Integer> paginas;
    private HashMap<String , Integer> clientes;

    public AnalizadorAccesosAServidor() 
    {
        accesos = new ArrayList<>();
        paginas = new HashMap<>();
        clientes = new HashMap<>();
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
        paginas.clear();                //cada vez que se inicia se resetea
        String cadenaAMostrar = null;
        int paginaMasRepetida = -1;         //guarda el valor mas buscado de las paginas
        for(Acceso accesoActual : accesos){         //hace un hashmap iniciando los valores diferentes a 0
            if(!paginas.containsKey(accesoActual.getPagina())){
                paginas.put(accesoActual.getPagina() , 0);
            }
        }
        for(Acceso acceso :accesos){        //indica la cantidad de veces que una pagina es buscada
            paginas.put(acceso.getPagina() , paginas.get(acceso.getPagina()) + 1);
        }
        for(String pagina : paginas.keySet()){          //consigue la pagina que mayor valor tiene en el hashmap
            if(pagina != null && paginas.get(pagina) > paginaMasRepetida){
                paginaMasRepetida = paginas.get(pagina);
                cadenaAMostrar = pagina;
            }
        }
        return cadenaAMostrar;
    }

    public String clienteConMasAccesosExitosos()
    {
        clientes.clear();                //cada vez que se inicia se resetea
        String cadenaAMostrar = null;
        int ipMasRepetida = -1;         //guarda la ip mas repetida de las paginas
        for(Acceso accesoActual : accesos){         //hace un hashmap iniciando los valores diferentes a 0
            if(!clientes.containsKey(accesoActual.getIp())){
                clientes.put(accesoActual.getIp() , 0);
            }
        }
        for(Acceso acceso :accesos){        //indica la cantidad de veces que un usuario ha hecho un acceso
            clientes.put(acceso.getIp() , clientes.get(acceso.getIp()) + 1);
        }
        for(String ip : clientes.keySet()){          //consigue el usuario con mas accesos en el hashmap
            if(ip != null && clientes.get(ip) >= ipMasRepetida){
                ipMasRepetida = clientes.get(ip);
                cadenaAMostrar = ip;
            }
        }
        System.out.println(clientes.keySet());
        System.out.println(clientes.values());
        return cadenaAMostrar;
    }
    
}

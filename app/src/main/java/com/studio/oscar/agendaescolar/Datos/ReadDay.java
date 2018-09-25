package com.studio.oscar.agendaescolar.Datos;

import android.util.Log;
import android.widget.Toast;

import com.studio.oscar.agendaescolar.Objetos.HClases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;

import static android.os.Environment.getExternalStorageDirectory;

public class ReadDay {

    public static ArrayList Read(int dia){
        ArrayList<HClases> arrayList = new ArrayList<>();
        switch (dia){
            case 0:
                arrayList = ReadLunes();
                break;
            case 1:
                arrayList = ReadMartes();
                break;
            case 2:
                arrayList = ReadMiercoles();
                break;
            case 3:
                arrayList = ReadJueves();
                break;
            case 4:
                arrayList = ReadViernes();
                break;
            case 7:
                arrayList = ReadAsign();
                break;
        }
        Collections.sort(arrayList);
        return arrayList;
    }


    public static ArrayList ReadAsign(){
        ArrayList<HClases> arrayList = new ArrayList<>();
        String name;
        HClases hClases;
        short nFile;

        File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.horario/"+"7"); // obtiene el acceso a la memoria interna y obtiene el directorio

        if(path.isDirectory()){
            File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.horario/"+"7").listFiles(); // obtiene la lista de archivos que existen en el directorio
            if(arrayFile != null){
                for(nFile = 0; nFile <= arrayFile.length; nFile++){
                    try{
                        name = arrayFile[nFile].getName();
                        File fileName = new File(path.getAbsolutePath(), name);
                        ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(fileName));

                        HClases aux = (HClases) entrada.readObject();
                        String asign = "Asignatura: " + aux.getAsignatura();
                        String profesor = "Docente: " + aux.getProfesor();
                        String aula = "Aula: " + aux.getAula();

                        hClases = new HClases(asign,profesor,aula);
                        arrayList.add(hClases);

                    }catch(FileNotFoundException ex){
                    }catch(IOException e){
                    }catch (ArrayIndexOutOfBoundsException e){
                    } catch (ClassNotFoundException e) {
                        //e.printStackTrace();
                    }
                } // end for
            }
        }else{}
        return arrayList;
    }

    public static ArrayList<HClases> ReadLunes(){
        ArrayList<HClases> arrayList = new ArrayList<>();
        String name;
        short nFile;

        File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.horario/"+"0"); // obtiene el acceso a la memoria interna y obtiene el directorio
        if(path.isDirectory()){
            File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.horario/"+"0").listFiles(); // obtiene la lista de archivos que existen en el directorio
            if(arrayFile != null){
                for(nFile = 0; nFile <= arrayFile.length; nFile++){
                    try{
                        name = arrayFile[nFile].getName();
                        File fileName = new File(path.getAbsolutePath(), name);
                        ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(fileName));

                        HClases aux = (HClases) entrada.readObject();
                        arrayList.add(aux);

                    }catch(FileNotFoundException ex){
                    }catch(IOException e){
                    }catch (ArrayIndexOutOfBoundsException e){
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } // end for
            }
        }else{}
        return arrayList;
    }

    public static ArrayList<HClases> ReadMartes(){
        ArrayList<HClases> arrayList = new ArrayList<>();
        String name;
        short nFile;

        File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.horario/"+"1"); // obtiene el acceso a la memoria interna y obtiene el directorio
        if(path.isDirectory()){
            File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.horario/"+"1").listFiles(); // obtiene la lista de archivos que existen en el directorio
            if(arrayFile != null){
                for(nFile = 0; nFile <= arrayFile.length; nFile++){
                    try{
                        Log.d("try","array: "+arrayFile.length);
                        name = arrayFile[nFile].getName();
                        File fileName = new File(path.getAbsolutePath(), name);
                        ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(fileName));

                        HClases aux = (HClases) entrada.readObject();
                        arrayList.add(aux);

                    }catch(FileNotFoundException ex){
                        ex.printStackTrace();
                    }catch(IOException e){
                        e.printStackTrace();
                    }catch (ArrayIndexOutOfBoundsException e){
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } // end for
            }
        }else{}
        Log.d("list",""+arrayList.size());
        return arrayList;
    }

    public static ArrayList<HClases> ReadMiercoles(){
        ArrayList<HClases> arrayList = new ArrayList<>();
        String name;

        short nFile;

        File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.horario/"+"2"); // obtiene el acceso a la memoria interna y obtiene el directorio
        if(path.isDirectory()){
            File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.horario/"+"2").listFiles(); // obtiene la lista de archivos que existen en el directorio
            if(arrayFile != null){
                for(nFile = 0; nFile <= arrayFile.length; nFile++){
                    try{
                        name = arrayFile[nFile].getName();
                        File fileName = new File(path.getAbsolutePath(), name);
                        ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(fileName));

                        HClases aux = (HClases) entrada.readObject();
                        arrayList.add(aux);

                    }catch(FileNotFoundException ex){
                        //Toast.makeText(getApplicationContext(),"Archivo no encontrado: "+ex.getMessage(),Toast.LENGTH_SHORT).show();
                    }catch(IOException e){
                        //Toast.makeText(getApplicationContext(), "Error IOE", Toast.LENGTH_SHORT).show();
                    }catch (ArrayIndexOutOfBoundsException e){
                        //Toast.makeText(getApplicationContext(), "Error Array: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } // end for
            }
        }else{}
        return arrayList;
    }

    public static ArrayList<HClases> ReadJueves(){
        ArrayList<HClases> arrayList = new ArrayList<>();
        String name;

        short nFile;

        File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.horario/"+"3"); // obtiene el acceso a la memoria interna y obtiene el directorio
        if(path.isDirectory()){
            File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.horario/"+"3").listFiles(); // obtiene la lista de archivos que existen en el directorio
            if(arrayFile != null){
                for(nFile = 0; nFile <= arrayFile.length; nFile++){
                    try{
                        name = arrayFile[nFile].getName();
                        File fileName = new File(path.getAbsolutePath(), name);
                        ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(fileName));

                        HClases aux = (HClases) entrada.readObject();
                        arrayList.add(aux);

                    }catch(FileNotFoundException ex){
                        //Toast.makeText(getApplicationContext(),"Archivo no encontrado: "+ex.getMessage(),Toast.LENGTH_SHORT).show();
                    }catch(IOException e){
                        //Toast.makeText(getApplicationContext(), "Error IOE", Toast.LENGTH_SHORT).show();
                    }catch (ArrayIndexOutOfBoundsException e){
                        //Toast.makeText(getApplicationContext(), "Error Array: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } // end for
            }
        }else{}
        return arrayList;
    }

    public static ArrayList<HClases> ReadViernes(){
        ArrayList<HClases> arrayList = new ArrayList<>();
        String name;

        short nFile;

        File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.horario/"+"4"); // obtiene el acceso a la memoria interna y obtiene el directorio
        if(path.isDirectory()){
            File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.horario/"+"4").listFiles(); // obtiene la lista de archivos que existen en el directorio
            if(arrayFile != null){
                for(nFile = 0; nFile <= arrayFile.length; nFile++){
                    try{
                        name = arrayFile[nFile].getName();
                        File fileName = new File(path.getAbsolutePath(), name);
                        ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(fileName));

                        HClases aux = (HClases) entrada.readObject();

                        arrayList.add(aux);

                    }catch(FileNotFoundException ex){
                        //Toast.makeText(getApplicationContext(),"Archivo no encontrado: "+ex.getMessage(),Toast.LENGTH_SHORT).show();
                    }catch(IOException e){
                        //Toast.makeText(getApplicationContext(), "Error IOE", Toast.LENGTH_SHORT).show();
                    }catch (ArrayIndexOutOfBoundsException e){
                        //Toast.makeText(getApplicationContext(), "Error Array: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } // end for
            }
        }else{
            //Snackbar.make(findViewById(android.R.id.content),"NO HAY ASIGNATURAS AGREGADAS",Snackbar.LENGTH_SHORT).show();
        }
        return arrayList;
    }

}

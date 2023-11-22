package com.mycompany.viuproyecto;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Asignatura Proyecto de ingenieria de software 
 * 
 * @author frami
 */

/**
* La clase Conexion es la que establece la conexión con la base de datos Firebase
*/
public class Conexion {
    
    /**
    * La clase Conexion es la que establece la conexión con la base de datos Firebase
    */
    public static Firestore db;
    
    /** En este caso utilizo un metodo de tipo Firestore para conectar aplicando la arquitectura MVC
     * 
     * @return abre Bd
     */
     public static Firestore getConnection() {
        FirestoreOptions firestoreOptions = null;
        try {
            firestoreOptions = FirestoreOptions.newBuilder()
                    .setCredentials(GoogleCredentials.fromStream(new FileInputStream("viuproyecto.json")))
                    .build();
        } catch (IOException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        Firestore dbMVC = firestoreOptions.getService();
        return dbMVC;
    }
}
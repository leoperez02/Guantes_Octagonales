package clases;

import java.sql.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Leo
 */
public class ConexionBD {

    static String bd = "agenda";
    static String login = "root";
    static String password = "N0m3l0s3.,01";
    static String url = "jdbc:mysql://localhost/" + bd;

    public ConexionBD(Object obj, char c) throws Exception {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/agenda", "root", "N0m3l0s3.,01");

            if (conn != null) {
                System.out.println("Conexión a base de datos " + url + " ... Ok");

                Statement stmt = conn.createStatement();

                if (c == 'M') {
                    PersonaMoral cliente = new PersonaMoral();
                    cliente = (PersonaMoral) obj;

                    String s1 = Validador.remove1(cliente.actividadComercial.rfc);
                    String s2 = Validador.remove1(cliente.actividadComercial.giroComercial);
                    String s3 = Validador.remove1(cliente.direccion.calle);
                    String s4 = Validador.remove1(cliente.direccion.numInterior);
                    String s5 = Validador.remove1(cliente.direccion.numExterior);
                    String s6 = Validador.remove1(cliente.direccion.codigoPostal);
                    String s7 = Validador.remove1(cliente.direccion.ciudad);
                    String s8 = Validador.remove1(cliente.direccion.pais);
                    stmt.executeUpdate("INSERT INTO cliente (RFC,Giro_comerciall,Calle,num_int,num_ext,CP,Ciudad,Pais) VALUES('" + s1 + "','" + s2 + "','" + s3 + "','" + s4 + "','" + s5 + "','" + s6 + "','" + s7 + "','" + s8 + "')");

                    stmt.close();
                    stmt = conn.createStatement();

                    s1 = cliente.actividadComercial.rfc;
                    s2 = cliente.razonSocial;
                    stmt.executeUpdate("INSERT INTO persona_moral (CLIENTE_RFC,Razon_social) VALUES('" + s1 + "','" + s2 + "')");

                    stmt.close();
                    //stmt = conn.createStatement();

                    System.out.println("Iniciando registro de teléfonos asociados...");

                    for (int i = 0; i < cliente.telefonos.size(); i++) {
                        System.out.println("Ciclo #: " + i);
                        java.util.Date d = new java.util.Date();
                        //int ID = Integer.parseInt(id);
                        stmt = conn.createStatement();
                        s1 = Validador.remove1(cliente.telefonos.get(i).ubicacion);
                        s2 = Validador.remove1(cliente.telefonos.get(i).numero);
                        s3 = Validador.remove1(cliente.actividadComercial.rfc);
                        String id = Integer.toString(d.getYear()) + Integer.toString(d.getMonth()) + Integer.toString(d.getDay()) + Integer.toString(d.getHours()) + Integer.toString(d.getMinutes()) + Integer.toString(d.getSeconds());
                        id += s2;
                        System.out.println(s1 + "  " + s2 + "  " + "  " + s3 + "  " + id);
                        stmt.executeUpdate("INSERT INTO telefono (Descripcion,Numero,CLIENTE_RFC,PERSONA_CONTACTO_Dni) VALUES('" + s1 + "','" + s2 + "','" + s3 + "','" + id + "') ");
                        stmt.close();

                    }

                    stmt.close();
                    //stmt = conn.createStatement();

                    System.out.println("Iniciando registro de medios digitales asociados...");

                    for (int i = 0; i < cliente.mediosDigitales.correos.size(); i++) {
                        System.out.println("Ciclo #: " + i);
                        java.util.Date d = new java.util.Date();
                        //int ID = Integer.parseInt(id);
                        stmt = conn.createStatement();
                        s1 = Validador.remove1(cliente.mediosDigitales.tipos.get(i));
                        s2 = Validador.remove1(cliente.mediosDigitales.correos.get(i));
                        s3 = Validador.remove1(cliente.actividadComercial.rfc);
                        String id = Integer.toString(d.getYear()) + Integer.toString(d.getMonth()) + Integer.toString(d.getDay()) + Integer.toString(d.getHours()) + Integer.toString(d.getMinutes()) + Integer.toString(d.getSeconds());
                        id += s2;
                        System.out.println(s1 + "  " + s2 + "  " + "  " + s3 + "  " + id);
                        if(s2.length()!=0)
                            stmt.executeUpdate("INSERT INTO medios_digitales (Tipo,Direccion_electronica,CLIENTE_RFC,PERSONA_CONTACTO_Dni) VALUES('" + s1 + "','" + s2 + "','" + s3 + "','" + id + "'   )");
                        stmt.close();
                    }

                    stmt.close();
                    //stmt = conn.createStatement();

                    for (int i = 0; i < cliente.contactos.size(); i++) {
                        java.util.Date d = new java.util.Date();
                        //int ID = Integer.parseInt(id);
                        stmt = conn.createStatement();
                        s1 = Validador.remove1(cliente.contactos.get(i).curp);
                        s2 = Validador.remove1(cliente.contactos.get(i).nombre.nombre);
                        s3 = Validador.remove1(cliente.contactos.get(i).nombre.aPaterno);
                        s4 = Validador.remove1(cliente.contactos.get(i).nombre.aMaterno);
                        s5 = Validador.remove1(cliente.contactos.get(i).cargo);
                        s6 = Validador.remove1(cliente.contactos.get(i).direccion.calle);
                        s7 = Validador.remove1(cliente.contactos.get(i).direccion.numInterior);
                        s8 = Validador.remove1(cliente.contactos.get(i).direccion.numExterior);
                        String s9 = Validador.remove1(cliente.contactos.get(i).direccion.codigoPostal);
                        String s10 = Validador.remove1(cliente.contactos.get(i).direccion.ciudad);
                        String s11 = Validador.remove1(cliente.contactos.get(i).direccion.pais);
                        String s12 = Validador.remove1(cliente.actividadComercial.rfc);
                        String id = Integer.toString(d.getYear()) + Integer.toString(d.getMonth()) + Integer.toString(d.getDay()) + Integer.toString(d.getHours()) + Integer.toString(d.getMinutes()) + Integer.toString(d.getSeconds());
                        id += s5;
                        stmt.executeUpdate("INSERT INTO persona_contacto (CURP,Nombre,Ap_Pat,Ap_Mat,Cargo,Calle,num_int,num_ext,CP,Ciudad,País,CLIENTE_RFC,Dni) VALUES ('" + s1 + "','" + s2 + "','" + s3 + "','" + s4 + "','" + s5 + "','" + s6 + "','" + s7 + "','" + s8 + "','" + s9 + "','" + s10 + "','" + s11 + "','" + s12 + "','" + id + "'    ) ");
                        stmt.close();

                        Contacto contacto = new Contacto();
                        contacto = cliente.contactos.get(i);
                        // Registro de datos del contacto...telefono y medios digitales

                        System.out.println("Iniciando registro de teléfonos asociados...");

                        for (int j = 0; j < contacto.telefonos.size(); j++) {
                            System.out.println("Ciclo #: " + j);
                            java.util.Date e = new java.util.Date();
                            //int ID = Integer.parseInt(id);
                            stmt = conn.createStatement();
                            s1 = Validador.remove1(contacto.telefonos.get(j).ubicacion);
                            s2 = Validador.remove1(contacto.telefonos.get(j).numero);
                            s3 = Validador.remove1(contacto.actividadComercial.rfc);
                            String id2 = Integer.toString(e.getYear()) + Integer.toString(e.getMonth()) + Integer.toString(e.getDay()) + Integer.toString(e.getHours()) + Integer.toString(e.getMinutes()) + Integer.toString(e.getSeconds());
                            id2 += s2;
                            System.out.println(s1 + "  " + s2 + "  " + "  " + s3 + "  " + id2);
                            stmt.executeUpdate("INSERT INTO telefono (Descripcion,Numero,CLIENTE_RFC,PERSONA_CONTACTO_Dni) VALUES('" + s1 + "','" + s2 + "','" + s3 + "','" + id2 + "') ");
                            stmt.close();

                        }

                        stmt.close();
                    //stmt = conn.createStatement();

                        System.out.println("Iniciando registro de medios digitales asociados...");

                        for (int j = 0; j < contacto.mediosDigitales.correos.size(); j++) {
                            System.out.println("Ciclo #: " + j);
                            java.util.Date e = new java.util.Date();
                            //int ID = Integer.parseInt(id);
                            stmt = conn.createStatement();
                            s1 = Validador.remove1(contacto.mediosDigitales.tipos.get(j));
                            s2 = Validador.remove1(contacto.mediosDigitales.correos.get(j));
                            s3 = Validador.remove1(contacto.actividadComercial.rfc);
                            String id2 = Integer.toString(e.getYear()) + Integer.toString(e.getMonth()) + Integer.toString(e.getDay()) + Integer.toString(e.getHours()) + Integer.toString(e.getMinutes()) + Integer.toString(e.getSeconds());
                            id2 += s2;
                            System.out.println(s1 + "  " + s2 + "  " + "  " + s3 + "  " + id2);
                            if(s2.length()!=0)
                                stmt.executeUpdate("INSERT INTO medios_digitales (Tipo,Direccion_electronica,CLIENTE_RFC,PERSONA_CONTACTO_Dni) VALUES('" + s1 + "','" + s2 + "','" + s3 + "','" + id2 + "'   )");
                            stmt.close();
                        }

                        stmt.close();

                    }

                    stmt.close();
                    stmt = conn.createStatement();

                } else {
                    //Persona fisica

                    PersonaFisica cliente = new PersonaFisica();
                    cliente = (PersonaFisica) obj;

                    String s1 = Validador.remove1(cliente.actividadComercial.rfc);
                    String s2 = Validador.remove1(cliente.actividadComercial.giroComercial);
                    String s3 = Validador.remove1(cliente.direccion.calle);
                    String s4 = Validador.remove1(cliente.direccion.numInterior);
                    String s5 = Validador.remove1(cliente.direccion.numExterior);
                    String s6 = Validador.remove1(cliente.direccion.codigoPostal);
                    String s7 = Validador.remove1(cliente.direccion.ciudad);
                    String s8 = Validador.remove1(cliente.direccion.pais);
                    stmt.executeUpdate("INSERT INTO cliente (RFC,Giro_comerciall,Calle,num_int,num_ext,CP,Ciudad,Pais) VALUES('" + s1 + "','" + s2 + "','" + s3 + "','" + s4 + "','" + s5 + "','" + s6 + "','" + s7 + "','" + s8 + "')");

                    stmt.close();
                    stmt = conn.createStatement();
                    
                    s1 = cliente.actividadComercial.rfc;
                    s2 = cliente.nombre.nombre;
                    s3 = cliente.nombre.aPaterno;
                    s4 = cliente.nombre.aMaterno;
                    s5 = cliente.curp;
                    stmt.executeUpdate("INSERT INTO persona_fisica (CLIENTE_RFC,Nombre,Ap_Pat,Ap_Mat,CURP) VALUES('" + s1 + "','" + s2 + "','"+s3+"','"+s4+"' ,'"+s5+"'   )");

                    stmt.close();
                    
                    System.out.println("Iniciando registro de teléfonos asociados...");

                    for (int i = 0; i < cliente.telefonos.size(); i++) {
                        System.out.println("Ciclo #: " + i);
                        java.util.Date d = new java.util.Date();
                        //int ID = Integer.parseInt(id);
                        stmt = conn.createStatement();
                        s1 = Validador.remove1(cliente.telefonos.get(i).ubicacion);
                        s2 = Validador.remove1(cliente.telefonos.get(i).numero);
                        s3 = Validador.remove1(cliente.actividadComercial.rfc);
                        String id = Integer.toString(d.getYear()) + Integer.toString(d.getMonth()) + Integer.toString(d.getDay()) + Integer.toString(d.getHours()) + Integer.toString(d.getMinutes()) + Integer.toString(d.getSeconds());
                        id += s2;
                        System.out.println(s1 + "  " + s2 + "  " + "  " + s3 + "  " + id);
                        stmt.executeUpdate("INSERT INTO telefono (Descripcion,Numero,CLIENTE_RFC,PERSONA_CONTACTO_Dni) VALUES('" + s1 + "','" + s2 + "','" + s3 + "','" + id + "') ");
                        stmt.close();

                    }

                    stmt.close();
                    //stmt = conn.createStatement();

                    System.out.println("Iniciando registro de medios digitales asociados...");

                    for (int i = 0; i < cliente.mediosDigitales.correos.size(); i++) {
                        System.out.println("Ciclo #: " + i);
                        java.util.Date d = new java.util.Date();
                        //int ID = Integer.parseInt(id);
                        stmt = conn.createStatement();
                        s1 = Validador.remove1(cliente.mediosDigitales.tipos.get(i));
                        s2 = Validador.remove1(cliente.mediosDigitales.correos.get(i));
                        s3 = Validador.remove1(cliente.actividadComercial.rfc);
                        String id = Integer.toString(d.getYear()) + Integer.toString(d.getMonth()) + Integer.toString(d.getDay()) + Integer.toString(d.getHours()) + Integer.toString(d.getMinutes()) + Integer.toString(d.getSeconds());
                        id += s2;
                        System.out.println(s1 + "  " + s2 + "  " + "  " + s3 + "  " + id);
                        if(s2.length()!=0)
                            stmt.executeUpdate("INSERT INTO medios_digitales (Tipo,Direccion_electronica,CLIENTE_RFC,PERSONA_CONTACTO_Dni) VALUES('" + s1 + "','" + s2 + "','" + s3 + "','" + id + "'   )");
                        stmt.close();
                    }

                    stmt.close();
                }
                stmt.close();
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println("Hubo un problema al intentar conectarse con la base de datos " + url);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }
}

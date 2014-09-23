/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stin.pemex.siop.util;

import com.stin.pemex.siop.model.Informacion;
import com.stin.pemex.siop.model.InformacionPozos;
import com.stin.pemex.siop.reader.ReadFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Marco
 */
public class TestClass {
     public String excutedReader(String fileName) {

        String res = null;
        String[] splitFileName;
        String[] splitArray;
        String proxTrd = "nada";
        String proxTrv = "nada";
        int contadorTR = 0;
        
        try {
            ReadFile obj = new ReadFile(fileName);
            ArrayList array = new ArrayList();
            array = obj.getAllInfoFile();
            ArrayList array1 = new ArrayList();
            array1 = obj.getResults(array);
            ArrayList array2 = new ArrayList();
            array2 = obj.getFinalResults(array1);
            Iterator<InformacionPozos> i = array2.iterator();
            //Contiene toda la informacion del pozo
            InformacionPozos infP = null;

            int con = 1;

            System.out.println("Ubicacion del archivo: " + fileName);

            while (i.hasNext()) {
                
                infP = i.next();
                System.out.println("x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x");
                System.out.println("-------------------------------------------------------");
                System.out.println("HEADER");
                System.out.println("-------------------------------------------------------");
                //Compañia
                System.out.println(SiopUtils.validateInfo(SiopUtils.splitFcn(infP.getCia(), ":", false)));
                //Complemento
                System.out.println(infP.getCia_Complement().toUpperCase().trim());
                //Fecha
                splitArray = SiopUtils.splitFcn(infP.getCia_Fecha().trim(), " ", false);
                System.out.println("Fecha SIOP: " + splitArray[1]);
                SiopUtils.dateFechaSiop(splitArray[1]);
                //REPORTE DIARIO DE OPERACIONES DEL
                System.out.println("Fecha Operacion: " + infP.getReportediario_Fecha());
                SiopUtils.dateFechaOperacion(infP.getReportediario_Fecha());
       
                
                //Proyecto:
                System.out.println(infP.getReportediario_proyecto());
                //Complemento
                System.out.println(infP.getReportediario_complemento());
                //Equipo
                splitArray = SiopUtils.splitFcn(infP.getEquipo(), " ", false);
                System.out.println("Equipo desde array: " + splitArray[1].trim());
                //Equipo-Conductor
                splitArray = SiopUtils.splitFcn(infP.getEquipo_conductor().trim(), " ", false);
                ArrayList cleanList = SiopUtils.RemoveEmptyElements(splitArray, false);
                ArrayList elements = SiopUtils.findListElement(cleanList);
                System.out.println("Conductor: " + elements.get(0));
                System.out.println("Esp. Rot-NM: " + elements.get(1));
                System.out.println("Actividad: " + elements.get(2));
                //Equipo-Inicio
                System.out.println(infP.getEquipo_inicio());
                //Pozo
                System.out.println(infP.getPozo());
                //Regresa la informacion concatenada de los comentarios
                //+++++++INFORMACION CENTRO DE GEOCIENCIA++++++++++++++
                System.out.println("-------------------------------------------------------");
                System.out.println("PROFUNDIDADES");
                System.out.println("-------------------------------------------------------");
                
                Iterator<HashMap<String, String>> iprof = infP.getProfundidadPorHoja().iterator();
                while (iprof.hasNext()) {

                    HashMap<String, String> mp = iprof.next();
                    //System.out.println("00:00 " + "|" + SiopUtils.verificaVacio(mp.get("profund_00_desarrollo")) + " | " + SiopUtils.verificaVacio(mp.get("profund_00_vertical")) + "|");
                    //System.out.println("24:00 " + "|" + SiopUtils.verificaVacio(mp.get("profund_24_desarrollo")) + " | " + SiopUtils.verificaVacio(mp.get("profund_24_vertical")) + "|");
                    //System.out.println("05:00 " + "|" + SiopUtils.verificaVacio(mp.get("profund_05_desarrollo")) + " | " + SiopUtils.verificaVacio(mp.get("profund_05_vertical"))+ "|");
                    //System.out.println("Ava/Ind    " + "|" + SiopUtils.verificaVacio(mp.get("profund_ava_ind_desarrollo")) + " | " + SiopUtils.verificaVacio(mp.get("profund_ava_ind_vertical")) + "|");
                    //System.out.println("Ult.Cont   " + "|" + SiopUtils.verificaVacio(mp.get("profund_ult_cont_desarrollo")) + " | " + SiopUtils.verificaVacio(mp.get("profund_ult_cont_vertical")) + "|");
                    //System.out.println("Prox. Cont " + "|" + SiopUtils.verificaVacio(mp.get("profund_prox_count_desarrollo")) + " | " + SiopUtils.verificaVacio(mp.get("profund_prox_count_vertical")) + "|");
                    //*****************CONVERTIR DECIMALES A FRACCIONES PARA ULTIMA Y PROXIMA TR**************************************
                   if (con == 1){
                    proxTrd = mp.get("profund_ultima_tr_desarrollo").toString().trim();
                    proxTrv = mp.get("profund_ultima_tr_vertical").toString().trim();
                    contadorTR = 0;
                   }
                    //System.out.println("Constantes: " + proxTrd +" - " + proxTrv);
                    //System.out.println("Variables:  " + mp.get("profund_ultima_tr_desarrollo").toString() + " - " + mp.get("profund_ultima_tr_vertical").toString());
                  if(SiopUtils.verificaTR(proxTrd, proxTrv, mp.get("profund_ultima_tr_desarrollo").toString(), mp.get("profund_ultima_tr_vertical").toString())){
                      contadorTR++;
                      //System.out.println("--SON IGUALITOS !!! " + contadorTR);
                      
                  }else{
                      contadorTR = 1;
                       //System.out.println("++NO SE PARECENN !!! " + contadorTR);
                       //proxTrd = mp.get("profund_ultima_tr_desarrollo").toString().trim();
                       //proxTrv = mp.get("profund_ultima_tr_vertical").toString().trim();  
                  }
                    //System.out.println("Ultima TR  " + "|" + SiopUtils.verificaVacio(mp.get("profund_ultima_tr_desarrollo")) + " | " + SiopUtils.verificaVacio(mp.get("profund_ultima_tr_vertical")) + "|");
                    //System.out.println("Prox. TR   " + "|" + SiopUtils.verificaVacio(mp.get("profund_prox_tr_desarrollo")) + " | " + SiopUtils.verificaVacio(mp.get("profund_prox_tr_vertical")) + "|");
                    //System.out.println("B.L        " + "|" + SiopUtils.verificaVacio(mp.get("profund_b_l_desarrollo")) + " | " + SiopUtils.verificaVacio(mp.get("profund_b_l_vertical")) + "|");
                    //System.out.println("C2         " + "|" + SiopUtils.verificaVacio(mp.get("profund_c2_desarrollo")) + " | " + SiopUtils.verificaVacio(mp.get("profund_c2_vertical")) + "|");
                    
                }
               
                System.out.println("-------------------------------------------------------");
                System.out.println("BARRENAS");
                System.out.println("-------------------------------------------------------");
                iprof = infP.getBarrenasPorHoja().iterator();
                while (iprof.hasNext()) {
                    HashMap<String, String> mb = iprof.next();
                    System.out.println("Marca      " + "|" + SiopUtils.verificaVacio(mb.get("barrena_marca_actual")) + " | " + SiopUtils.verificaVacio(mb.get("barrena_marca_anterior")) + "|");
                    System.out.println("Serie      " + "|" + SiopUtils.verificaVacio(mb.get("barrena_serie_actual")) + " | " + SiopUtils.verificaVacio(mb.get("barrena_serie_anterior")) + "|");
                    System.out.println("Diametro   " + "|" + SiopUtils.verificaVacio(mb.get("barrena_diametro_actual")) + " | " + SiopUtils.verificaVacio(mb.get("barrena_diametro_anterior")) + "|");
                    System.out.println("Tipo       " + "|" + SiopUtils.verificaVacio(mb.get("barrena_tipo_actual")) + " | " + SiopUtils.verificaVacio(mb.get("barrena_tipo_anterior")) + "|");
                    System.out.println("Toberas    " + "|" + SiopUtils.verificaVacio(mb.get("barrena_toberas_actual")) + " | " + SiopUtils.verificaVacio(mb.get("barrena_toberas_anterior")) + "|");
                    System.out.println("Mts. Perf  " + "|" + SiopUtils.verificaProfundidadDesarrollo(mb.get("barrena_mts_perf_actual")) + " | " + SiopUtils.verificaProfundidadDesarrollo(mb.get("barrena_mts_perf_anterior")) + "|");
                    System.out.println("Hrs. Op    " + "|" + SiopUtils.verificaHorasOP(mb.get("barrena_hors_operacion_actual")) + " | " + SiopUtils.verificaHorasOP(mb.get("barrena_hors_operacion_anterior")) + "|");
                    System.out.println("Prom x m   " + "|" + SiopUtils.verificaVacio(mb.get("barrena_prom_x_m_actual")) + " | " + SiopUtils.verificaVacio(mb.get("barrena_prom_x_m_anterior")) + "|");
                    System.out.println("3 Ult. Mts " + "|" + SiopUtils.eliminaApostrofes(mb.get("barrena_3_ult_mts_actual")) + " | " + SiopUtils.eliminaApostrofes(mb.get("barrena_3_ult_mts_anterior")) + "|");
                    System.out.println("Costo x m  " + "|" + SiopUtils.verificaVacio(mb.get("barrena_costo_x_m_actual")) + " | " + SiopUtils.verificaVacio(mb.get("barrena_costo_x_m_anterior")) + "|");
                    System.out.println("Amp. Inic  " + "|" + SiopUtils.verificaProfundidadDesarrollo(mb.get("barrena_amp_inic_actual")) + " | " + SiopUtils.verificaProfundidadDesarrollo(mb.get("barrena_amp_inic_anterior")) + "|");
                    System.out.println("Des. IADC  " + "|" + SiopUtils.verificaVacio(mb.get("barrena_des_AIDC_actual")) + " | " + SiopUtils.verificaVacio(mb.get("barrena_des_AIDC_anterior")) + "|");
                }
                System.out.println("-------------------------------------------------------");
                System.out.println("HERRAMIENTAS");
                System.out.println("-------------------------------------------------------");
                iprof = infP.getHerramientasPorHoja().iterator();
                while (iprof.hasNext()) {
                    HashMap<String, String> mh = iprof.next();
                    if(mh != null){
                        System.out.println("Existe Herramienta");
                    }else{
                        System.out.println("NO Existe Herramienta");
                    }
                  if(SiopUtils.verificaHerramienta(mh.get("herramienta_reng_1"), mh.get("herramienta_cant_reng_1"), mh.get("herramienta_long_reng_1"), mh.get("herramienta_obs_reng_1")))
                    System.out.println("|" + mh.get("herramienta_reng_1") + " | " + mh.get("herramienta_cant_reng_1") + "|" + "|" + mh.get("herramienta_long_reng_1") + " | " + mh.get("herramienta_obs_reng_1") + "|");
                  if(SiopUtils.verificaHerramienta(mh.get("herramienta_reng_2"), mh.get("herramienta_cant_reng_2"), mh.get("herramienta_long_reng_2"), mh.get("herramienta_obs_reng_2")))
                    System.out.println("|" + mh.get("herramienta_reng_2") + " | " + mh.get("herramienta_cant_reng_2") + "|" + "|" + mh.get("herramienta_long_reng_2") + " | " + mh.get("herramienta_obs_reng_2") + "|");
                  if(SiopUtils.verificaHerramienta(mh.get("herramienta_reng_3"), mh.get("herramienta_cant_reng_3"), mh.get("herramienta_long_reng_3"), mh.get("herramienta_obs_reng_3")))
                    System.out.println("|" + mh.get("herramienta_reng_3") + " | " + mh.get("herramienta_cant_reng_3") + "|" + "|" + mh.get("herramienta_long_reng_3") + " | " + mh.get("herramienta_obs_reng_3") + "|");
                  if(SiopUtils.verificaHerramienta(mh.get("herramienta_reng_4"), mh.get("herramienta_cant_reng_4"), mh.get("herramienta_long_reng_4"), mh.get("herramienta_obs_reng_4")))
                    System.out.println("|" + mh.get("herramienta_reng_4") + " | " + mh.get("herramienta_cant_reng_4") + "|" + "|" + mh.get("herramienta_long_reng_4") + " | " + mh.get("herramienta_obs_reng_4") + "|");
                  if(SiopUtils.verificaHerramienta(mh.get("herramienta_reng_5"), mh.get("herramienta_cant_reng_5"), mh.get("herramienta_long_reng_5"), mh.get("herramienta_obs_reng_5")))
                    System.out.println("|" + mh.get("herramienta_reng_5") + " | " + mh.get("herramienta_cant_reng_5") + "|" + "|" + mh.get("herramienta_long_reng_5") + " | " + mh.get("herramienta_obs_reng_5") + "|");
                  if(SiopUtils.verificaHerramienta(mh.get("herramienta_reng_6"), mh.get("herramienta_cant_reng_6"), mh.get("herramienta_long_reng_6"), mh.get("herramienta_obs_reng_6")))
                    System.out.println("|" + mh.get("herramienta_reng_6") + " | " + mh.get("herramienta_cant_reng_6") + "|" + "|" + mh.get("herramienta_long_reng_6") + " | " + mh.get("herramienta_obs_reng_6") + "|");
                  if(SiopUtils.verificaHerramienta(mh.get("herramienta_reng_7"), mh.get("herramienta_cant_reng_7"), mh.get("herramienta_long_reng_7"), mh.get("herramienta_obs_reng_7")))  
                    System.out.println("|" + mh.get("herramienta_reng_7") + " | " + mh.get("herramienta_cant_reng_7") + "|" + "|" + mh.get("herramienta_long_reng_7") + " | " + mh.get("herramienta_obs_reng_7") + "|");
                  if(SiopUtils.verificaHerramienta(mh.get("herramienta_reng_8"), mh.get("herramienta_cant_reng_8"), mh.get("herramienta_long_reng_8"), mh.get("herramienta_obs_reng_8")))  
                    System.out.println("|" + mh.get("herramienta_reng_8") + " | " + mh.get("herramienta_cant_reng_8") + "|" + "|" + mh.get("herramienta_long_reng_8") + " | " + mh.get("herramienta_obs_reng_8") + "|");
                  if(SiopUtils.verificaHerramienta(mh.get("herramienta_reng_9"), mh.get("herramienta_cant_reng_9"), mh.get("herramienta_long_reng_9"), mh.get("herramienta_obs_reng_9")))
                    System.out.println("|" + mh.get("herramienta_reng_9") + " | " + mh.get("herramienta_cant_reng_9") + "|" + "|" + mh.get("herramienta_long_reng_9") + " | " + mh.get("herramienta_obs_reng_9") + "|");
                  if(SiopUtils.verificaHerramienta(mh.get("herramienta_reng_10"), mh.get("herramienta_cant_reng_10"), mh.get("herramienta_long_reng_10"), mh.get("herramienta_obs_reng_10")))
                    System.out.println("|" + mh.get("herramienta_reng_10") + " | " + mh.get("herramienta_cant_reng_10") + "|" + "|" + mh.get("herramienta_long_reng_10") + " | " + mh.get("herramienta_obs_reng_10") + "|");
                  if(SiopUtils.verificaHerramienta(mh.get("herramienta_reng_11"), mh.get("herramienta_cant_reng_11"), mh.get("herramienta_long_reng_11"), mh.get("herramienta_obs_reng_11")))
                     System.out.println("|" + mh.get("herramienta_reng_11") + " | " + mh.get("herramienta_cant_reng_11") + "|" + "|" + mh.get("herramienta_long_reng_11") + " | " + mh.get("herramienta_obs_reng_11") + "|");
                  if(SiopUtils.verificaHerramienta(mh.get("herramienta_reng_12"), mh.get("herramienta_cant_reng_12"), mh.get("herramienta_long_reng_12"), mh.get("herramienta_obs_reng_12"))) 
                    System.out.println("|" + mh.get("herramienta_reng_12") + " | " + mh.get("herramienta_cant_reng_12") + "|" + "|" + mh.get("herramienta_long_reng_12") + " | " + mh.get("herramienta_obs_reng_12") + "|");
                }
                System.out.println("-------------------------------------------------------");
                System.out.println("NUCLEOS");
                System.out.println("-------------------------------------------------------");
                iprof = infP.getNucleosPorHoja().iterator();
                while (iprof.hasNext()) {
                    HashMap<String, String> mn = iprof.next();
//                    if(SiopUtils.verificaNucleo(mn.get("nucleos_r_row_0_col_0"),mn.get("int_prog_row_0_col_1"), mn.get("int_disp_row_0_col_2")))
//                    System.out.println("|" + mn.get("nucleos_r_row_0_col_0").trim() + " | " + mn.get("int_prog_row_0_col_1") + "|" + " | " + mn.get("int_disp_row_0_col_2") + "|");
//                    if(SiopUtils.verificaNucleo(mn.get("nucleos_r_row_1_col_0"),mn.get("int_prog_row_1_col_1"), mn.get("int_disp_row_1_col_2")))
//                    System.out.println("|" + SiopUtils.verificaVacio(mn.get("nucleos_r_row_1_col_0")) + " | " + SiopUtils.verificaVacio(mn.get("int_prog_row_1_col_1")) + "|" + " | " + SiopUtils.verificaVacio(mn.get("int_disp_row_1_col_2")) + "|");
//                    if(SiopUtils.verificaNucleo(mn.get("nucleos_r_row_2_col_0"),mn.get("int_prog_row_2_col_1"), mn.get("int_disp_row_2_col_2")))
//                    System.out.println("|" + SiopUtils.verificaVacio(mn.get("nucleos_r_row_2_col_0")) + " | " + SiopUtils.verificaVacio(mn.get("int_prog_row_2_col_1")) + "|" + " | " + SiopUtils.verificaVacio(mn.get("int_disp_row_2_col_2")) + "|");
//                    if(SiopUtils.verificaNucleo(mn.get("nucleos_r_row_3_col_0"),mn.get("int_prog_row_3_col_1"), mn.get("int_disp_row_3_col_2")))
//                    System.out.println("|" + SiopUtils.verificaVacio(mn.get("nucleos_r_row_3_col_0")) + " | " + SiopUtils.verificaVacio(mn.get("int_prog_row_3_col_1")) + "|" + " | " + SiopUtils.verificaVacio(mn.get("int_disp_row_3_col_2")) + "|");
//                    if(SiopUtils.verificaNucleo(mn.get("nucleos_r_row_4_col_0"),mn.get("int_prog_row_4_col_1"), mn.get("int_disp_row_4_col_2")))
//                    System.out.println("|" + SiopUtils.verificaVacio(mn.get("nucleos_r_row_4_col_0")) + " | " + SiopUtils.verificaVacio(mn.get("int_prog_row_4_col_1")) + "|" + " | " + SiopUtils.verificaVacio(mn.get("int_disp_row_4_col_2")) + "|");
//                
                }
                System.out.println("-------------------------------------------------------");
                System.out.println("LODO");
                System.out.println("-------------------------------------------------------");
                iprof = infP.getLodoPorHoja().iterator();
                while (iprof.hasNext()) {
                    HashMap<String, String> ml = iprof.next();
//                    Map.Entry e = (Map.Entry)iprof.next();
//                    System.out.println(e.getKey() + " " + e.getValue());
                    if(SiopUtils.verificaLodo(ml.get("lodo_row_0_col_23").trim())){
                        int alc = 0;
                        if (ml.get("Alc. ") == null) {
                            alc = alc;
                            //System.out.println("SIN ALCALINIDAD");
                        } else {
                            //System.out.println("CON ALCALINIDAD");
                            alc = Integer.valueOf(SiopUtils.verificaProfundidadDesarrollo(ml.get("Alc. ").trim()));
                        }
                       
//                        System.out.println("Lodo     |" + SiopUtils.verificaVacio(ml.get("lodo_row_0_col_23").toString().trim())+ "|");
//                        System.out.println("Dens     |" + SiopUtils.verificaVacio(ml.get("Dens").toString().trim()) + "|");
//                        System.out.println("Visc     |" + SiopUtils.verificaVacio(ml.get("Visc").toString().trim()) + "|");
//                        System.out.println("Temp     |" + SiopUtils.verificaVacio(ml.get("Temp °C").toString().trim()) + "|");
//                        System.out.println("Arena    |" + SiopUtils.verificaVacio(ml.get("%Arena").toString().trim()) + "|");
//                        System.out.println("Filtrado |" + SiopUtils.verificaVacio(ml.get("Filtrado").toString().trim()) + "|");
//                        System.out.println("Calcio   |" + SiopUtils.verificaVacio(ml.get("Calcio").toString().trim()) + "|");
//                        System.out.println("Enjarre  |" + SiopUtils.verificaVacio(ml.get("Enjarre").toString().trim()) + "|");
//                        //System.out.println("Alc      |" + SiopUtils.verificaVacio(ml.get("Alc. ").toString().trim()) + "|");
//                        System.out.println("Alc      |" + alc + "|");
//                        System.out.println("Gel 0    |" + SiopUtils.verificaVacio(ml.get("Gel 0 ").toString().trim()) + "|");
//                        System.out.println("Gel 10   |" + SiopUtils.verificaVacio(ml.get("Gel 10 ").toString().trim()) + "|");
//                        System.out.println("Cloruros |" + SiopUtils.verificaVacio(ml.get("Cloruros").toString().trim()) + "|");
//                        System.out.println("PH       |" + SiopUtils.verificaVacio(ml.get("PH").toString().trim()) + "|");
//                        System.out.println("Solidos  |" + SiopUtils.verificaVacio(ml.get("%Solidos").toString().trim()) + "|");
//                        System.out.println("Aceite   |" + SiopUtils.verificaVacio(ml.get("%Aceite").toString().trim()) + "|");
//                        System.out.println("Agua     |" + SiopUtils.verificaVacio(ml.get("%Agua").toString().trim()) + "|");
//                        System.out.println("VA       |" + SiopUtils.verificaVacio(ml.get("VA").toString().trim()) + "|");
//                        System.out.println("VP       |" + SiopUtils.verificaVacio(ml.get("VP").toString().trim()) + "|");
//                        System.out.println("YP       |" + SiopUtils.verificaVacio(ml.get("YP").toString().trim()) + "|");
//                        System.out.println("Emul     |" + SiopUtils.verificaVacio(ml.get("Emul").toString().trim()) + "|");
//                        System.out.println("R.A.A.   |" + SiopUtils.verificaVacio(ml.get("R.A.A.").toString().trim()) + "|");
//                        System.out.println("MBT      |" + SiopUtils.verificaVacio(ml.get("MBT").toString().trim()) + "|");
                    }else{
                        //System.out.println("****NO SE ENCONTRO LODO");
                    }
                    
                }
                //******************************
                System.out.println("-------------------------------------------------------");
                System.out.println("OPERACION");
                System.out.println("-------------------------------------------------------");
                Iterator<Informacion> it = infP.getAllInfo().iterator();
                Informacion info = null;
                
                while (it.hasNext()) {
                    info = it.next();
                    System.out.println("" + info.getFechaInicio() + "-" + info.getFechaFinal() + " -> " + info.getActividad());
                }

                System.out.println("informacion del Archivo: ");
                System.out.println("Archivo :" + infP.getNomFile());
                System.out.println("Nombre de la pagina: " + infP.getNomPag());
                System.out.println("Numero de Columnas:" + infP.getNumCols());
                System.out.println("#:" + con++);

                res = "Archivo leido correctamente";
            }
        } catch (Exception e) {
            res = "Error: " + e.toString();
        }
        return res;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.clases;

import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.List;

/**
 *
 * @author ALEXANDER
 */
public class Validacion {

    public Boolean validarCaracteres(KeyEvent evt) {
        char entrada = evt.getKeyChar();
        if (Character.isLetter(entrada)) {
            evt.consume();
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public void gaussMaxi(List<Ecuacion> ecuaciones, int filas, int columnas) {
        int columnaTotal = filas + columnas;
        int columna1 = columnas;
        DecimalFormat decimales = new DecimalFormat("0.000000");
        double[][] matriz = new double[filas][columnaTotal];

        for (int j = columna1; j < columnaTotal; j++) {
            matriz[0][j] = 0.0;
        }
        int i1 = 1;
        int j1 = columna1;
        for (int i = 1; i < filas; i++) {
            for (int j = columna1; j < columnaTotal - 1; j++) {
                if (i1 == i && j1 == j) {
                    matriz[i][j] = 1.0;
                } else {
                    matriz[i][j] = 0.0;
                }
            }
            j1++;
            i1++;
        }

        for (int i = 0; i < ecuaciones.size(); i++) {
            matriz[i][0] = ecuaciones.get(i).getVariableX1();
            matriz[i][1] = ecuaciones.get(i).getVariableX2();
            matriz[i][columnaTotal - 1] = ecuaciones.get(i).getResultado();
        }
        String v2[] = new String[filas];
        int v3[] = new int[filas];
        int v4[] = new int[filas];

        int co = 0;
        while (true) {
            if (co == columna1) {
                boolean vax1 = false, vx2 = false;
                for (int i = 0; i < v2.length; i++) {
                    if ("x2".equals(v2[i])) {
                        vx2 = true;
                    }
                    if ("x1".equals(v2[i])) {
                        vax1=true;
                    }
                }
                if (vax1 && vx2) {
                    break;
                } else {
                    co--;
                }
            }

            int f = 0;
            int c = 0;
            double negativo = 0;
            //Encontramos el pivote mas negativo de la columna
            for (int i = 0; i < columna1; i++) {
                if (matriz[0][i] < negativo) {
                    negativo = matriz[0][i];
                    c = i;
                }
            }
            double menor = 0;
            double v1[] = new double[filas - 1];
            //encontramos el pivote mas negativo de la fila
            int h = 0;
            for (int i = 1; i < filas; i++) {
                if (matriz[i][c] != 0.0) {
                    v1[h] = matriz[i][columnaTotal - 1] / matriz[i][c];
                } else {
                   v1[h] = 0.0;
                }
                h++;
            }
            //Encontramos el menor numero del arreglo 
            menor = v1[0];
            for (int i = 0; i < filas - 1; i++) {

                if (v1[i] <= menor && v1[i]>0.0) {
                    menor = v1[i];
                    f = i + 1;
                }
            }

            //Se divide entre el pivote
            double pivo = matriz[f][c];
            for (int i = 0; i < columnaTotal; i++) {
                if (pivo != 0.0) {
                    matriz[f][i] = matriz[f][i] / pivo;
                }else{
                    matriz[f][i] = 0.0 ;
                }
            }

            for (int i = 1; i < filas; i++) {
                if (i == f) {
                    v2[i] = "x" + (c + 1);
                    v3[i] = c + 1;
                    v4[i] = i;
                }
                if (v3[i] <= 0) {

                    v2[i] = "d" + (i);
                }
            }

            //convertir los ceros de la fila
            for (int i = 0; i < filas; i++) {
                if (i != f) {
                    double guar = 0;
                    guar = -matriz[i][c];
                    for (int j = 0; j < columnaTotal; j++) {
                        matriz[i][j] = (guar * matriz[f][j]) + matriz[i][j];
                    }
                }
            }

            co = 0;
            for (int i = 0; i < columna1; i++) {
                if (matriz[0][i] >= 0) {
                    co++;
                }
            }
        }

        //Mostrando el arreglo
        for (int x = 0; x < matriz.length; x++) {
            System.out.print("|");
            for (int y = 0; y < matriz[x].length; y++) {
                System.out.print(decimales.format(matriz[x][y]));
                if (y != matriz[x].length - 1) {
                    System.out.print("\t");
                }
            }
            System.out.println("|");
        }

        System.out.println("RESULTADO\n");
        System.out.println("z = " + matriz[0][columnaTotal - 1] + "\n");
        for (int i = 1; i < filas; i++) {
            char m1[] = v2[i].toCharArray();
            if (m1[0] == 'x') {
                System.out.println(" " + v2[i] + " = " + matriz[v4[i]][columnaTotal - 1] + "\n");
            }
        }
    }

    Double prueba = null;

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cooppred;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.classifiers.trees.j48.ClassifierSplitModel;
import weka.classifiers.trees.j48.ClassifierTree;
import weka.classifiers.trees.j48.NBTreeNoSplit;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;


/**
 *
 * @author Kenny
 */
public class Pred {

    public static String dirdataset = "src/src/dataset.arff";
    public static String dirtemporal = "temporal.arff";

    
    //Inicia el entrenamiento -- Requiere Predecir -- Requiere arff de test
    public static String makePred(String edad, String ant, String monto,String tipo)  throws Exception{
        String salida = "No se presta";
        //Cargar y clasificar el DataSet
        DataSource ds = new DataSource(dirdataset);
        Instances ins = ds.getDataSet();
        ins.setClassIndex(3);
        
        
        //Seleccionamos el clasificador
        Classifier tj48;
        
        if (tipo.equals("NaiveBayes")) {
            tj48 = new NaiveBayes();
        } else {
            tj48 = new RandomForest();
        }
        
        //Bayes
        //NaiveBayes tj48 = new NaiveBayes();
        //RandomForest
        //RandomForest tj48 = new RandomForest();
        
        //Asignar las instancias al clasificador
        tj48.buildClassifier(ins);
        
        System.out.println(tj48.toString());
        TextLog.add(tj48.toString());
        
        //salida = setTest(tj48);
        
        //Instance instance;
        //instance = setAttr(edad,ant);
        //System.out.println(instance.toString());
        //salida = makeTest(tj48,instance);
        writeArff(edad,ant,monto);
        salida = makeTest(tj48);
                
        return salida;
    }
    
    
    //Inicia la prediccion
    public static String makeTest(Classifier model)  throws Exception{ //, Instance instance
        String salida="No se presta";
        //Cargar y clasificar el DataSet
        //DataSource ds = new DataSource("src/src/ORDINARIO_FIRMA_TEST.arff");
        DataSource ds = new DataSource(dirtemporal);
        Instances ins = ds.getDataSet();
        ins.setClassIndex(3);
        
        double pred = model.classifyInstance(ins.instance(0));
        //System.out.println("???????????????");
        //System.out.println(pred);
        //System.out.println(ins.instance(0).toString());
        //System.out.println(instance.toString());
        //System.out.println("???????????????");
        String predString = ins.classAttribute().value((int) pred);
        System.out.println("Prediccion: " + predString);
        TextLog.add("Prediccion: " + predString);
        salida = predString;
        
        return salida;
    }
    
    
    
    
      
        

    public static void writeArff(String edad, String antiguedad, String monto) throws IOException {
        String ruta = dirtemporal;
        File archivo = new File(ruta);
        BufferedWriter bw;
        if(archivo.exists()) {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write("%xdss");
            bw.write("\r\n");
            
            bw.write("@relation 'tipo de prestamo'");
            bw.write("\r\n");
            
            bw.write("%ATRIBUTOS POR ORDEN DE APARICIÓN EN LA DESCRIPCIÓN");
            bw.write("\r\n");
            
            bw.write("@attribute monto INTEGER");
            bw.write("\r\n");
            
            bw.write("@attribute antiguedad INTEGER");
            bw.write("\r\n");
            
            bw.write("@attribute edad INTEGER");
            bw.write("\r\n");
            
            bw.write("@attribute prestamo {'A SOLA FIRMA',EXTRAORDINARIO}");
            bw.write("\r\n");
            
            bw.write("%DATOS DE LOS PARTICIPANTES");
            bw.write("\r\n");
            
            bw.write("@data");
            bw.write("\r\n");
            
            bw.write(monto + "," + antiguedad + "," + edad + ",?");
            bw.write("\r\n");
            
        } else {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write("%xdss");
            bw.write("\r\n");
            
            bw.write("@relation 'tipo de prestamo'");
            bw.write("\r\n");
            
            bw.write("%ATRIBUTOS POR ORDEN DE APARICIÓN EN LA DESCRIPCIÓN");
            bw.write("\r\n");
            
            bw.write("@attribute monto INTEGER");
            bw.write("\r\n");
            
            bw.write("@attribute antiguedad INTEGER");
            bw.write("\r\n");
            
            bw.write("@attribute edad INTEGER");
            bw.write("\r\n");
            
            bw.write("@attribute prestamo {'A SOLA FIRMA',EXTRAORDINARIO}");
            bw.write("\r\n");
            
            bw.write("%DATOS DE LOS PARTICIPANTES");
            bw.write("\r\n");
            
            bw.write("@data");
            bw.write("\r\n");
            
            bw.write(monto + "," + antiguedad + "," + edad + ",?");
            bw.write("\r\n");
        }
        bw.close();
    }
    

    
}



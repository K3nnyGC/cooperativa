
package cooppred;

import static cooppred.Pred.dirdataset;
import java.util.ArrayList;
import java.util.List;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;



/**
 *
 * @author Kenny
 */
public class DatasetValue {
    
    public static List<RowIns> lista = new ArrayList<RowIns>() ;
    
    public static void load() throws Exception{
        lista.clear();
        ConverterUtils.DataSource ds = new ConverterUtils.DataSource(Pred.dirdataset);
        Instances ins = ds.getDataSet();
        ins.setClassIndex(3);
        
        RowIns fila;
        for (int i = 0; i < ins.numInstances(); i++) {
            fila = new RowIns();
            fila.id = i+1;
            fila.monto = "" + ins.instance(i).value(0)/100;
            fila.antiguedad = "" + (int) ins.instance(i).value(1);
            fila.edad = "" + (int) ins.instance(i).value(2);
            fila.prestamo = "" + ins.classAttribute().value((int) ins.instance(i).value(3));
            lista.add(fila);
        }
    }
    
}

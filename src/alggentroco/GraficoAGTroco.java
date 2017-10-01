/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alggentroco;

/**
 *
 * @author antonio
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class GraficoAGTroco extends JFrame{
    
    public GraficoAGTroco(String applicationTitle, int [] dadosMediaResult, int [] dadosMelhoresResult, int esperado) {
        super(applicationTitle);
        //Y = Erro ou algo assim; X = Gerações
        JFreeChart lineChart = ChartFactory.createXYLineChart("A.G. TROCO: Gerações X Médias","Geração" ,"Média", criarDataset(dadosMediaResult, dadosMelhoresResult, esperado), PlotOrientation.VERTICAL, true, true, false);
        
        ChartPanel painel = new ChartPanel(lineChart);
        
        //Configurações Visuais
        XYPlot plot = lineChart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
        
        renderer.setSeriesPaint( 0 , Color.BLUE );
        renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
        
        renderer.setSeriesPaint( 1 , Color.RED );
        renderer.setSeriesStroke( 1 , new BasicStroke( 4.0f ) );
        
        renderer.setSeriesPaint( 2 , Color.GREEN );
        renderer.setSeriesStroke( 2 , new BasicStroke( 8.0f ) );
        
        plot.setBackgroundPaint(Color.DARK_GRAY);
        plot.setRenderer(renderer);
        
        ValueAxis range = plot.getRangeAxis();
        //range.setRange(50, 80);//alterar para exibir com ampliação melhor
        setSize(640 , 480 );
        //painel.setPreferredSize(new java.awt.Dimension( 640 , 480 ));
        setContentPane(painel);
    }
    
    private XYDataset criarDataset(int [] dadosMediaResult, int [] dadosMelhoresResult, int esperado){
        XYSeriesCollection dataset = new XYSeriesCollection(); 
        
        //Valores para teste, não representam experimento real
        /*dataset.addValue(78, "cromossomos", "Geracao 1");
        dataset.addValue(60, "cromossomos", "Geracao 2");
        dataset.addValue(56, "cromossomos", "Geracao 3");
        dataset.addValue(48, "cromossomos", "Geracao 4");
        dataset.addValue(46, "cromossomos", "Geracao 5");
        dataset.addValue(42, "cromossomos", "Geracao 6");
        dataset.addValue(39, "cromossomos", "Geracao 7");
        dataset.addValue(38, "cromossomos", "Geracao 8");
        dataset.addValue(37, "cromossomos", "Geracao 9");
        dataset.addValue(36, "cromossomos", "Geracao 10");
        
        dataset.addValue(36, "cromossomoX", "Geracao 10");
        return dataset;*/
        XYSeries valoresMed = new XYSeries("Média dos Cromossomos Selecionados");
        for(int i = 0; i < dadosMediaResult.length; i++){
            valoresMed.add((i+1), dadosMediaResult[i]);//Indice (Geração), Valor da Média   
        }
        
        XYSeries valoresMelhores = new XYSeries("Melhores Valores por Geração");
        for(int i = 0; i< dadosMelhoresResult.length; i++){
            valoresMelhores.add((i+1), dadosMelhoresResult[i]);
        }
        
        XYSeries valorfinal = new XYSeries("Esperado");
        valorfinal.add(dadosMelhoresResult.length , esperado);//No "último" lugar, colocamos o valor esperado para melhor visualização
        
        dataset.addSeries(valoresMelhores);
        dataset.addSeries(valoresMed);
        dataset.addSeries(valorfinal);
        return dataset;        
    
    
    
    }
    
    public void constroiGrafico(int [] dadosMediaResult, int [] dadosMelhoresResult, int esperado){//Receber dados por aqui também
        GraficoAGTroco teste = new GraficoAGTroco("Gráfico de Gerações", dadosMediaResult, dadosMelhoresResult, esperado);
        teste.pack();
        RefineryUtilities.centerFrameOnScreen(teste);
        teste.setVisible(true);
    
    
    
    
    }
    
    
    
    
    
    
    
}

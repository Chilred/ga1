/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gen_aufg1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Chilred-pc
 */
public class GUIGenom extends JFrame {

    JPanel pnLeftBox;
    JPanel pnRightBox;

    JLabel lGenLength;
    JLabel lGenCount;
    JLabel lMaxGenerations;
    JLabel lRecombinationRate;
    JLabel lMutationRate;
    JLabel lReplicationScheme;
    JLabel lRecombinationScheme;
    JLabel lInitRate;
    JLabel lRuns;
    JLabel lOutputFile;
    JLabel lProtectionCheck;
    JLabel lStartSimulation;
    JLabel lS;

    JTextField tfGenLength;
    JTextField tfGenCount;
    JTextField tfMaxGenerations;
    JTextField tfPcStart;
    JTextField tfPcEnd;
    JTextField tfPcStep;
    JTextField tfPmStart;
    JTextField tfPmEnd;
    JTextField tfPmStep;
    JTextField tfReplicationScheme;
    JTextField tfRecombinationScheme;
    JTextField tfInitRate;
    JTextField tfS;
    JTextField tfOutputFile;

    JCheckBox cbProtectionCheck;
    JComboBox cbReplication;
    JComboBox cbRecombinationScheme;

    JButton btStartSimulation;

    JTextArea taResults;

    public GUIGenom() {
        setTitle("Genetische Algorithmen");
        setSize(500, 500);
        setLocation(300, 100);
        Listener li = new Listener();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cp = getContentPane();
        setLayout(new BoxLayout(cp, BoxLayout.X_AXIS));

        pnLeftBox = new JPanel();
        pnLeftBox.setPreferredSize(new Dimension(30, 30));

        pnRightBox = new JPanel();
        pnRightBox.setPreferredSize(new Dimension(10, 10));

        lGenLength = new JLabel("GenLength");
        lGenCount = new JLabel("GenCount");
        lMaxGenerations = new JLabel("MaxGenerations");
        lRecombinationRate = new JLabel("RecombinationRate");
        lMutationRate = new JLabel("MutationRate");
        lReplicationScheme = new JLabel("ReplicationScheme");
        lRecombinationScheme = new JLabel("RecombinationScheme");
        lInitRate = new JLabel("InitRate");
        lRuns = new JLabel("Runs");
        lOutputFile = new JLabel("Output File");
        lProtectionCheck = new JLabel("ProtectionCheck");
        lStartSimulation = new JLabel("Start Simulation");
        lS = new JLabel("s ");

        tfGenLength = new JTextField("200", 10);
        tfGenCount = new JTextField("200", 10);
        tfMaxGenerations = new JTextField("3000", 10);
        tfReplicationScheme = new JTextField("10x10", 10);
        String[] replicationString = {"10x10", "RankBest"};
        cbReplication = new JComboBox(replicationString);
        String[] recombinationString = {"crossover", "transposition"};
        cbRecombinationScheme = new JComboBox(recombinationString);
        cbReplication.setSelectedIndex(0);
        cbRecombinationScheme.setSelectedIndex(0);

        tfInitRate = new JTextField("0.05", 10);
        double pc = 0.7; //0.1
        double pcEnd = 0.9; //0.9
        double pcStep = 0.02; //0.02
        double pm = 0.02;  //0.00 
        double pmEnd = 0.05;//0.05
        double pmStep = 0.002; //0.002     

        tfS = new JTextField(5);
        tfOutputFile = new JTextField("04-3D.txt", 10);
        tfPcStart = new JTextField("0.1", 3);
        tfPcEnd = new JTextField("0.9", 3);
        tfPcStep = new JTextField("0.02", 3);
        tfPmStart = new JTextField("0.00", 3);
        tfPmEnd = new JTextField("0.05", 3);
        tfPmStep = new JTextField("0.002", 3);
        tfS = new JTextField("2", 5);

        cbProtectionCheck = new JCheckBox();
        btStartSimulation = new JButton("Start Simulation");
        btStartSimulation.setActionCommand("btn_Simulation");
        btStartSimulation.addActionListener(li);

        JPanel pnGenLength = new JPanel();
        pnGenLength.add(lGenLength);
        pnGenLength.add(tfGenLength);
        pnLeftBox.add(pnGenLength);

        JPanel pnGenCount = new JPanel();
        pnGenCount.add(lGenCount);
        pnGenCount.add(tfGenCount);
        pnLeftBox.add(pnGenCount);

        JPanel pnMaxGenerations = new JPanel();
        pnMaxGenerations.add(lMaxGenerations);
        pnMaxGenerations.add(tfMaxGenerations);
        pnLeftBox.add(pnMaxGenerations);

        JPanel pnRecombinationRate = new JPanel();
        pnRecombinationRate.add(lRecombinationRate);
        pnRecombinationRate.add(tfPcStart);
        pnRecombinationRate.add(tfPcEnd);
        pnRecombinationRate.add(tfPcStep);
        pnLeftBox.add(pnRecombinationRate);

        JPanel pnMutationRate = new JPanel();
        pnMutationRate.add(lMutationRate);
        pnMutationRate.add(tfPmStart);
        pnMutationRate.add(tfPmEnd);
        pnMutationRate.add(tfPmStep);
        pnLeftBox.add(pnMutationRate);

        JPanel pnReplicationScheme = new JPanel();
        pnReplicationScheme.add(lReplicationScheme);
        pnReplicationScheme.add(cbReplication);
        pnLeftBox.add(pnReplicationScheme);

        JPanel pnRecombinationScheme = new JPanel();
        pnRecombinationScheme.add(lRecombinationScheme);
        pnRecombinationScheme.add(cbRecombinationScheme);
        pnLeftBox.add(pnRecombinationScheme);

        JPanel pnInitRate = new JPanel();
        pnInitRate.add(lInitRate);
        pnInitRate.add(tfInitRate);
        pnLeftBox.add(pnInitRate);

        JPanel pnS = new JPanel();
        pnS.add(lS);
        pnS.add(tfS);
        pnLeftBox.add(pnS);

        JPanel pnOutputFile = new JPanel();
        pnOutputFile.add(lOutputFile);
        pnOutputFile.add(tfOutputFile);
        pnLeftBox.add(pnOutputFile);

        JPanel pnProtectionCheck = new JPanel();
        pnProtectionCheck.add(lProtectionCheck);
        pnProtectionCheck.add(cbProtectionCheck);
        pnLeftBox.add(pnProtectionCheck);

        JPanel pnStartSimulation = new JPanel();
        pnStartSimulation.add(lStartSimulation);
        pnStartSimulation.add(btStartSimulation);
        pnLeftBox.add(pnStartSimulation);

        taResults = new JTextArea();
        taResults.setPreferredSize(new Dimension(200, 400));
        pnRightBox.add(taResults);

        cp.add(pnLeftBox);
        cp.add(pnRightBox);

        setResizable(false);
        setVisible(true);
    }

    public class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            if (cmd.equals("btn_Simulation")) {
                try {
                    startSimulation();
                } catch (IOException ex) {
                    Logger.getLogger(GUIGenom.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void startSimulation() throws IOException {
        long startTime = System.currentTimeMillis();
        double pc = Double.parseDouble(tfPcStart.getText()); //0.1
        double pcEnd = Double.parseDouble(tfPcEnd.getText()); //0.9
        double pcStep = Double.parseDouble(tfPcStep.getText()); //0.02
        double pm = Double.parseDouble(tfPmStart.getText()); //0.00 
        double pmEnd = Double.parseDouble(tfPmEnd.getText());//0.05
        double pmStep = Double.parseDouble(tfPmStep.getText());; //0.002
        double initrate = Double.parseDouble(tfInitRate.getText()); //0.05
        int gencnt = Integer.parseInt(tfGenCount.getText());
        int genlen = Integer.parseInt(tfGenLength.getText());
        int max_generation = Integer.parseInt(tfMaxGenerations.getText());
        boolean protection = cbProtectionCheck.isSelected();
        String replication_scheme = (String) cbReplication.getSelectedItem();
        String recombinationScheme = (String) cbRecombinationScheme.getSelectedItem();
        double s = Integer.parseInt(tfS.getText());
        Double[] rankList = {};
        
        if (replication_scheme.equals("RankBest")) {
            rankList = getRankList(gencnt, s);
        }

        String result = "";
        for (double i = pc; i < pcEnd; i += pcStep) {
            for (double j = pm; j < pmEnd; j += pmStep) {
                Run run = new Run(10, i, j, initrate, gencnt, genlen, max_generation, protection, replication_scheme, recombinationScheme, rankList);
                result = result + run.getResult();
               // setTextfield(result);
            }
            result = result + "\r\n";
        }
      
//        Run run = new Run(100, 0.7, 0.02, initrate, gencnt, genlen, max_generation, protection, replication_scheme, recombinationScheme, rankList);
//        result = result + run.getResult();
        
        
        //End timekeeping
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        result = result + "\r\n" + "# Gesamtzeit" + totalTime + " ms";        
        writeInFile(result, tfOutputFile.getText());
        System.out.println(totalTime + "ms");
    }

    public void setTextfield(String text) {
        taResults.append(text);
    }

    // creates RankList
    public static Double[] getRankList(int n, double s) {
        double ps = 0;
        double ps_kum = 0;
        Double[] rankList = new Double[n];
        for (int r = 0; r < n; r++) {
            ps = (2 - s) / n + (2 * r * (s - 1)) / (n * (n-1));
            ps_kum += ps;
            rankList[r] = ps_kum;
        }
        return rankList;
    }

    public static void writeInFile(String result, String filename) {
        try {
            String content = result;

            File file = new File("C:/Users/Chilred-pc/workspace/gen_aufg1/" + filename);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

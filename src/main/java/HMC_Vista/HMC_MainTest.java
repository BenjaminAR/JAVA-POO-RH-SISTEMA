/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HMC_Vista;

import javax.swing.JFrame;

/**
 *
 * @author Benjamin
 */
public class HMC_MainTest {
        public static void main(String[] args) {
        JFrame frame = new JFrame("Sistema Recursos Humanos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);

        frame.setContentPane(new HMC_FrmPrincipal());

        frame.setVisible(true);
    }
}

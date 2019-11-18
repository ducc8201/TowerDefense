import ObjectsGame.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class controlPanel extends JPanel implements ActionListener {

    GameStage gameStage;

    NormalTower normalTower;
    SniperTower sniperTower;
    MachineGunTower machineGunTower;
    Map1 map1;
    ArrayList<Enemy> listTank;
    ArrayList<Tower> towers;

    boolean flagTower = false;
    int countTank = 5;
    int countNorTower = 0;
    int click = 0;
    int towerNumber = -1;
    int countClickObj = 0;

    Graphics2D g2d;
    JButton JButtonNormalTower = new JButton(new ImageIcon(getClass().getResource("/Defaultsize/NormalTower.png")));
    JButton JButtonSniperTower = new JButton(new ImageIcon(getClass().getResource("/Defaultsize/SniperTower.png")));
    JButton JButtonMachineGunTower = new JButton(new ImageIcon(getClass().getResource("/Defaultsize/MachineGunTower.png")));

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == JButtonNormalTower && click == 0){

            click = 1;

            addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    if (click == 1) {
                        flagTower = true;
                        normalTower = new NormalTower(e.getX() - 32, e.getY() - 32);
                    }
                }
            });

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (click == 1) {
                        towers.add(new NormalTower(e.getX() -32, e.getY() -32));
                        countNorTower++;
                    }
                }
            });
        }
        else if (e.getSource() == JButtonSniperTower && click == 0) {
            click = 2;

            addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    if (click == 2) {
                        flagTower = true;
                        sniperTower = new SniperTower(e.getX() - 32, e.getY() - 32);
                    }
                }
            });

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (click == 2) {
                        towers.add(new SniperTower(e.getX() -32, e.getY() -32));
                        countNorTower++;
                    }
                }
            });
        }
        else if (e.getSource() == JButtonMachineGunTower && click == 0) {
            click = 3;

            addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    if (click == 3) {
                        flagTower = true;
                        machineGunTower = new MachineGunTower(e.getX() - 32, e.getY() - 32);
                    }
                }
            });

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (click == 3) {
                        towers.add(new MachineGunTower(e.getX() -32, e.getY() -32));
                        countNorTower++;
                    }
                }
            });
        }
        else if ((e.getSource() == JButtonNormalTower || e.getSource() == JButtonSniperTower || e.getSource() == JButtonMachineGunTower ) && click != 0){
            flagTower = false;
            click = 0;
        }
    }

    public controlPanel(GameStage gameStage) {
        this.gameStage = gameStage;
        map1 = new Map1();
        normalTower = new NormalTower(1056, 60);
        listTank = new ArrayList<>();
        towers = new ArrayList<>();
        setLayout(null);
        MenuOfItem();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                for(int i = 0 ;i < countNorTower ; i++){
                    if(towers.get(i).getX() < e.getX() && e.getX() <towers.get(i).getX() + 64 && e.getY() > towers.get(i).getY() && e.getY() < towers.get(i).getY()+64 ) {
                        towerNumber = i;
                        countClickObj++;
                    }
                }
            }
        });

    }

    private void MenuOfItem() {
        JButtonNormalTower.setBounds(1056, 60, 64, 64 );
        JButtonSniperTower.setBounds(1056, 150, 64 , 64 );
        JButtonMachineGunTower.setBounds(1146, 60, 64, 64);

        add(JButtonNormalTower);
        add(JButtonSniperTower);
        add(JButtonMachineGunTower);

        JButtonNormalTower.addActionListener(this);
        JButtonSniperTower.addActionListener(this);
        JButtonMachineGunTower.addActionListener(this);
    }

    public void setupObj(){
        for(int a = 0 ; a < countTank ; a++){
            listTank.add(new TankerEnemy(32,672 + a*128));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g2d = (Graphics2D) g;

        map1.render(g2d);

        if (countTank <= 11) {
            setupObj();

            for(int a = 0 ; a < countTank ; a++){
                listTank.get(a).render(g2d);
            }

            if(listTank.get(countTank - 1).getX() >  960){
                listTank.clear();
                countTank = countTank +2;
            }
        }

        if (countNorTower > 0) {
            for (int i = 0; i < countNorTower; i++) {
                towers.get(i).render(g2d);
            }
        }

        if (flagTower == true ) {
            switch (click) {
                case 1:
                    g2d.drawOval((int) normalTower.getX() - (int) (normalTower.getShootRange() - 32),(int) normalTower.getY() - (int) (normalTower.getShootRange() - 32) , (int) normalTower.getShootRange()*2, (int) normalTower.getShootRange()*2);
                    normalTower.render(g2d);
                    break;
                case 2:
                    g2d.drawOval((int) sniperTower.getX() - (int) (sniperTower.getShootRange() - 32),(int) sniperTower.getY() - (int) (sniperTower.getShootRange() - 32), (int) sniperTower.getShootRange()*2, (int) sniperTower.getShootRange()*2);
                    sniperTower.render(g2d);
                    break;
                case 3:
                    g2d.drawOval((int) machineGunTower.getX() - (int) (machineGunTower.getShootRange() - 32),(int) machineGunTower.getY() - (int) (machineGunTower.getShootRange() - 32), (int) machineGunTower.getShootRange()*2, (int) machineGunTower.getShootRange()*2);
                    machineGunTower.render(g2d);
                    break;
            }

        }

        if (towerNumber != -1 && countClickObj %2 != 0) {
            g2d.drawOval((int) towers.get(towerNumber).getX() - (int) (towers.get(towerNumber).getShootRange() - 32), (int) towers.get(towerNumber).getY() - (int) (towers.get(towerNumber).getShootRange() - 32), (int) towers.get(towerNumber).getShootRange()*2, (int) towers.get(towerNumber).getShootRange()*2  );
        }
    }
}

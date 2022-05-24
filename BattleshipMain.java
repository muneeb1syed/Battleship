import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class BattleshipMain extends JFrame implements ActionListener{
    JPanel humanPnl = new JPanel();
    JPanel compPnl = new JPanel();
    JPanel northPnl = new JPanel();
    JPanel southPnl = new JPanel();
    JPanel westPnl = new JPanel();
    JPanel eastPnl = new JPanel();
    JPanel centerPnl = new JPanel();

    JButton restart = new JButton("Restart");
    JButton orientationFlip = new JButton("Set To Vertical");
    JButton setDiff = new JButton("Set GOD AI");
    JButton[] humanButtons = new JButton[100];
    JButton[] compButtons = new JButton[100];
    byte[] humanArr = new byte[100];
    byte[] compArr = new byte[100];

    JLabel title = new JLabel("Welcome to Battleship!");
    JLabel diffLbl = new JLabel("Difficulty: Smart AI");
    JLabel winLbl = new JLabel();
    JLabel playerShips = new JLabel("Player's Ships:");
    JLabel compShips = new JLabel("Computer's Ships:");

    JLabel compCarrierLbl = new JLabel("Aircraft Carrier");
    JLabel compBattleshipLbl = new JLabel("Battleship");
    JLabel compDestroyerLbl = new JLabel("Destroyer");
    JLabel compSubmarineLbl = new JLabel("Submarine");
    JLabel compPatrolLbl = new JLabel("Patrol Boat");

    JLabel playCarrierLbl = new JLabel("Aircraft Carrier");
    JLabel playBattleshipLbl = new JLabel("Battleship");
    JLabel playDestroyerLbl = new JLabel("Destroyer");
    JLabel playSubmarineLbl = new JLabel("Submarine");
    JLabel playPatrolLbl = new JLabel("Patrol Boat");

    GridLayout board = new GridLayout(10, 10, 2, 2);
    GridLayout shipNames = new GridLayout(17,1,0,0);
    FlowLayout titleLyt = new FlowLayout(FlowLayout.CENTER);
    FlowLayout boardLayout = new FlowLayout(FlowLayout.CENTER);

    Color water = new Color(102, 179, 255);
    Color shipColor = new Color(191, 191, 191);
    Color destroyed = new Color(170, 0, 0);
    Font titleFont = new Font("Impact", Font.PLAIN, 40);
    Font winFont = new Font("Impact", Font.BOLD, 40);
    Font labelFont = new Font("Impact", Font.PLAIN, 18);

    ImageIcon Logo = new ImageIcon("Battleship3.jpg");

    Ship aircraftCarrier = new Ship("Aircraft Carrier", 5);
    Ship battleship = new Ship("Battleship", 4);
    Ship destroyer = new Ship("Destroyer", 3);
    Ship submarine = new Ship("Submarine", 3);
    Ship patrolBoat = new Ship("Patrol Boat", 2);

    Ship compAircraftCarrier = new Ship("Aircraft Carrier", 5);
    Ship compBattleship = new Ship("Battleship", 4);
    Ship compDestroyer = new Ship("Destroyer", 3);
    Ship compSubmarine = new Ship("Submarine", 3);
    Ship compPatrolBoat = new Ship("Patrol Boat", 2);

    ArrayList<Ship> shipList = new ArrayList<Ship>();
    ArrayList<Ship> compShipList = new ArrayList<Ship>();

    private boolean pregame = true, orientation = true, win = false;
    private int shipCounter = 0, compShipCounter = 0, difficulty = 1, count = 0;
    private String destroyedShip = "None";

    public BattleshipMain() {
        super("Battleship");
        setSize(1440, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        makeButtons();

        shipList.add(aircraftCarrier);
        shipList.add(battleship);
        shipList.add(destroyer);
        shipList.add(submarine);
        shipList.add(patrolBoat);

        compShipList.add(compAircraftCarrier);
        compShipList.add(compBattleship);
        compShipList.add(compDestroyer);
        compShipList.add(compSubmarine);
        compShipList.add(compPatrolBoat);

        //west panel (human board)
        boardLayout.setHgap(25);
        westPnl.setLayout(boardLayout);
        westPnl.add(humanPnl);
        westPnl.setBackground(Color.RED);
        humanPnl.setLayout(board);
        humanPnl.setPreferredSize(new Dimension(600, 600));
        humanPnl.setBackground(Color.BLACK);
        add(westPnl, BorderLayout.WEST);

        //east panel (computer board)
        eastPnl.setLayout(boardLayout);
        eastPnl.add(compPnl);
        eastPnl.setBackground(Color.RED);
        compPnl.setLayout(board);
        compPnl.setPreferredSize(new Dimension(600, 600));
        compPnl.setBackground(Color.BLACK);
        add(eastPnl, BorderLayout.EAST);

        //north panel
        northPnl.setPreferredSize(new Dimension(1440, 150));
        northPnl.setBackground(Color.RED);
        northPnl.setLayout(titleLyt);
        titleLyt.setVgap(40);
        title.setFont(titleFont);
        //northPnl.add(title);
        add(northPnl, BorderLayout.NORTH);
        JLabel image = new JLabel();
        image.setIcon(Logo);
        northPnl.add(image);

        //south panel
        winLbl.setSize(200, 200);
        winLbl.setFont(winFont);
        southPnl.add(winLbl);

        southPnl.setPreferredSize(new Dimension(1440, 100));
        southPnl.setBackground(Color.RED);
        add(southPnl, BorderLayout.SOUTH);

        //center panel
        centerPnl.setBackground(Color.RED);
        centerPnl.setLayout(shipNames);
        restart.addActionListener(this);
        orientationFlip.addActionListener(this);
        setDiff.addActionListener(this);
        diffLbl.setFont(new Font("Impact", Font.PLAIN, 18));
        centerPnl.add(diffLbl);
        centerPnl.add(restart);
        centerPnl.add(orientationFlip);
        centerPnl.add(setDiff);

        playerShips.setFont(labelFont);
        centerPnl.add(playerShips);
        centerPnl.add(compCarrierLbl);
        centerPnl.add(compBattleshipLbl);
        centerPnl.add(compDestroyerLbl);
        centerPnl.add(compSubmarineLbl);
        centerPnl.add(compPatrolLbl);

        compShips.setFont(labelFont);
        centerPnl.add(compShips);
        centerPnl.add(playCarrierLbl);
        centerPnl.add(playBattleshipLbl);
        centerPnl.add(playDestroyerLbl);
        centerPnl.add(playSubmarineLbl);
        centerPnl.add(playPatrolLbl);
        add(centerPnl, BorderLayout.CENTER);

        setVisible(true);
        validate();
    }

    public void makeButtons() {
        for(int i = 0; i < 100; i++) {
            compButtons[i] = new JButton();
            compButtons[i].addActionListener(this);
            compButtons[i].setBackground(water);
            compButtons[i].setOpaque(true);
            compButtons[i].setBorderPainted(false);
            compPnl.add(compButtons[i]);

            humanButtons[i] = new JButton();
            humanButtons[i].addActionListener(this);
            humanButtons[i].setBackground(water);
            humanButtons[i].setOpaque(true);
            humanButtons[i].setBorderPainted(false);
            humanPnl.add(humanButtons[i]);
        }
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        Object temp = "";

        if(source == restart) {
            resetBoard();
        }
        if(pregame) {
            if(source == orientationFlip) {
                if(orientation) {
                    orientation = false;
                    orientationFlip.setText("Set To Horizontal");
                }
                else {
                    orientation = true;
                    orientationFlip.setText("Set To Vertical");
                }
            }
            if(source == setDiff) {
                if(difficulty == 2) {
                    setDiff.setText("Set Smart AI");
                    diffLbl.setText("Difficulty: Noob AI");
                    difficulty = 0;
                }
                else if(difficulty == 0) {
                    setDiff.setText("Set GOD AI");
                    diffLbl.setText("Difficulty: Smart AI");
                    difficulty = 1;
                }
                else if(difficulty == 1) {
                    setDiff.setText("Set Noob AI");
                    diffLbl.setText("Difficulty: GOD AI");
                    difficulty = 2;
                }
            }
            for(int i = 0; i < 100; i++) {
                if(source == humanButtons[i]) {
                    if(checkShip(true, shipList.get(shipCounter++), orientation, i)){
                        //disable the button
                    }
                }
            }
            if(shipCounter > 4) {
                while(compShipCounter < 5) {
                    int randNum = (int)(Math.random() * 100);
                    int randOrientation = (int)(Math.random() * 2);
                    if(randOrientation == 0) orientation = true;
                    else if(randOrientation == 1) orientation = false;
                    checkShip(false, compShipList.get(compShipCounter++), orientation, randNum);
                }
                pregame = false;
                orientationFlip.setEnabled(false);
                setDiff.setEnabled(false);
            }
        }
        else if (!win){
            for(int i = 0; i < 100; i++) {
                if(source == compButtons[i]) {
                    if(compArr[i] == 0) {
                        compButtons[i].setBackground(Color.WHITE);
                        compArr[i] = 2;
                        if(!(checkPlayerWin() || checkComputerWin()))
                            temp = humanButtons[compTurn(difficulty)];
                    }
                    else if(compArr[i] == 1) {
                        compButtons[i].setBackground(Color.RED);
                        compArr[i] = 3;
                        findShip(false, i).takeHit();
                        if(findShip(false, i).isDestroyed()) {
                            destroyedShip = findShip(false, i).getName();
                            if(destroyedShip == "Aircraft Carrier")
                                playCarrierLbl.setForeground(destroyed);
                            else if(destroyedShip == "Battleship")
                                playBattleshipLbl.setForeground(destroyed);
                            else if(destroyedShip == "Destroyer")
                                playDestroyerLbl.setForeground(destroyed);
                            else if(destroyedShip == "Submarine")
                                playSubmarineLbl.setForeground(destroyed);
                            else if(destroyedShip == "Patrol Boat")
                                playPatrolLbl.setForeground(destroyed);

                            for(int location : findShip(false, i).getShipLocations()) {
                                compButtons[location].setBackground(destroyed);
                                compArr[location] = 4;
                            }
                        }
                        if(!(checkPlayerWin() || checkComputerWin()))
                            temp = humanButtons[compTurn(difficulty)];
                    }
                }
            }
            for(int i = 0; i < 100; i++) {
                if(temp == humanButtons[i]) {
                    if(humanArr[i] == 0) {
                        humanButtons[i].setBackground(Color.WHITE);
                        humanArr[i] = 2;
                    }
                    else if(humanArr[i] == 1) {
                        humanButtons[i].setBackground(Color.RED);
                        humanArr[i] = 3;
                        findShip(true, i).takeHit();
                        if(findShip(true, i).isDestroyed()) {
                            destroyedShip = findShip(true, i).getName();
                            if(destroyedShip == "Aircraft Carrier")
                                compCarrierLbl.setForeground(destroyed);
                            else if(destroyedShip == "Battleship")
                                compBattleshipLbl.setForeground(destroyed);
                            else if(destroyedShip == "Destroyer")
                                compDestroyerLbl.setForeground(destroyed);
                            else if(destroyedShip == "Submarine")
                                compSubmarineLbl.setForeground(destroyed);
                            else if(destroyedShip == "Patrol Boat")
                                compPatrolLbl.setForeground(destroyed);

                            for(int location : findShip(true, i).getShipLocations()) {
                                humanButtons[location].setBackground(destroyed);
                                humanArr[location] = 4;
                            }
                        }
                    }
                    checkComputerWin();
                }
            }
        }
    }

    public void resetBoard() {
        for(JButton a: compButtons)
            a.setBackground(water);
        for(JButton a: humanButtons)
            a.setBackground(water);
        for(int i = 0; i < 100; i++) {
            humanArr[i] = 0;
            compArr[i] = 0;
        }
        for(Ship a : shipList) {
            a.setDefaultHealth();
        }
        for(Ship a : compShipList) {
            a.setDefaultHealth();
        }

        compCarrierLbl.setForeground(new Color(0, 0, 0));
        compBattleshipLbl.setForeground(new Color(0, 0, 0));
        compSubmarineLbl.setForeground(new Color(0, 0, 0));
        compDestroyerLbl.setForeground(new Color(0, 0, 0));
        compPatrolLbl.setForeground(new Color(0, 0, 0));
        playCarrierLbl.setForeground(new Color(0, 0, 0));
        playBattleshipLbl.setForeground(new Color(0, 0, 0));
        playSubmarineLbl.setForeground(new Color(0, 0, 0));
        playDestroyerLbl.setForeground(new Color(0, 0, 0));
        playPatrolLbl.setForeground(new Color(0, 0, 0));

        shipCounter = 0;
        compShipCounter = 0;
        count = 0;
        orientationFlip.setEnabled(true);
        setDiff.setEnabled(true);
        pregame = true;
        orientation = true;
        win = false;
        orientationFlip.setText("Set to Vertical");
        winLbl.setText("");
    }

    public boolean checkShip(boolean human, Ship ship, boolean horizontal, int startNum) {
        byte limit = (byte)(10 - ship.getSize());

        if(horizontal && startNum % 10 <= limit && !inTheWay(human, ship, horizontal, startNum)){
            //System.out.println("Case 1");
            setShip(human, ship, horizontal, startNum);
            return true;
        }
        else if((!horizontal && startNum/10 <= limit) && (!inTheWay(human, ship, horizontal, startNum))) {
            //System.out.println("Case 2");
            setShip(human, ship, horizontal, startNum);
            return true;
        }
        else{
            if(human) {
                //System.out.println("Case 3");
                shipCounter--;
                return false;
            }
            else {
                //System.out.println("Case 4");
                compShipCounter--;
                return false;
                //System.out.println(compShipCounter);
            }
        }
    }

    public boolean inTheWay(boolean human, Ship ship, boolean horizontal, int startNum) {
        if(horizontal) {
            for(int i = 0; i < ship.getSize(); i++) {
                if(human && humanArr[startNum + i] != 0)
                    return true;
                else if(!human && compArr[startNum + i] != 0)
                    return true;
            }
        }
        else
            for(int i = 0; i < ship.getSize() * 10; i += 10) {
                if(human && humanArr[startNum + i] != 0)
                    return true;
                else if(!human && compArr[startNum + i] != 0)
                    return true;
            }
        return false;
    }

    public void setShip(boolean human, Ship ship, boolean horizontal, int startNum) {
        if(horizontal)
            for(int i = 0; i < ship.getSize(); i++) {
                if(human) {
                    humanButtons[startNum + i].setBackground(shipColor);
                    humanArr[startNum + i] = 1;
                }
                else {
                    compArr[startNum + i] = 1;
                }
                ship.setShipLocations(i, startNum + i);
            }
        else
            for(int i = 0; i < ship.getSize() * 10; i += 10) {
                if(human) {
                    humanButtons[startNum + i].setBackground(shipColor);
                    humanArr[startNum + i] = 1;
                }
                else {
                    compArr[startNum + i] = 1;
                }
                ship.setShipLocations(i/10, startNum + i);
            }
    }

    public boolean checkPlayerWin(){
        int [] destroyedArr = new int[5];
        for (int i = 0; i<5; i++)
            if(compShipList.get(i).isDestroyed())
                destroyedArr[i] = 1;
        if(destroyedArr[0]== 1 && destroyedArr[1]== 1 && destroyedArr[2]== 1 && destroyedArr[3]== 1 && destroyedArr[4]== 1) {
            System.out.println("Human Wins");
            win = true;
            winLbl.setText("Player Wins!!");
            return true;
        }
        else
            return false;
    }

    public boolean checkComputerWin(){
        int [] destroyedArr = new int[5];
        for (int i = 0; i<5; i++)
            if(shipList.get(i).isDestroyed())
                destroyedArr[i] = 1;
        if(destroyedArr[0]== 1 && destroyedArr[1]== 1 && destroyedArr[2]== 1 && destroyedArr[3]== 1 && destroyedArr[4]== 1) {
            System.out.println("Computer Wins");
            win = true;
            winLbl.setText("Computer Wins!!");
            for(int i = 0; i < 100; i++) {
                if(compArr[i] == 1) {
                    compButtons[i].setBackground(shipColor);
                }
            }
            return true;
        }
        else
            return false;
    }

    public int compTurn(int level) {
        int randNum = (int)(Math.random() * 100);
        if(level == 2) {
            while(humanArr[randNum] != 1) {
                randNum = (int)(Math.random() * 100);
            }
        }
        else if(level == 1) {
            for(int i = 0; i < 100; i++) {
                if(humanArr[i] == 3) {//find a spot which is hit but not sunk
                    if(i % 10 < 9 && humanArr[i+1] < 2)
                        return i + 1;
                    if(i % 10 > 0 && humanArr[i-1] < 2)
                        return i - 1;
                    if(i / 10 > 0 && humanArr[i-10] < 2)
                        return i - 10;
                    if(i / 10 < 9 && humanArr[i+10] < 2)
                        return i + 10;
                }
            }
            int randTens, randOnes;
            if(count < 50) {
                do {
                    randTens = (int)(Math.random() * 10);//gets 0-9, will mult by 10 later
                    randOnes = (int)(Math.random() * 5) * 2;//0-8 even
                    if(randTens % 2 == 1) randOnes++;
                }while(humanArr[randTens * 10 + randOnes] > 1);
                count++;
                return randTens * 10 + randOnes;
            }
            while(humanArr[randNum] > 1)
                randNum = (int)(Math.random() * 100);
        }
        else {
            while(humanArr[randNum] > 1)
                randNum = (int)(Math.random() * 100);
        }
        return randNum;
    }

    public Ship findShip(boolean human, int location) {
        if(human) {
            for(Ship a : shipList)
                for(int i = 0; i < a.getSize(); i++) {
                    if(a.getShipLocations()[i] == location)
                        return a;
                }
        }
        else {
            for(Ship a : compShipList)
                for(int i = 0; i < a.getSize(); i++)
                    if(a.getShipLocations()[i] == location)
                        return a;
        }
        System.out.println("\nError in findShip(). Did not return a ship. Returned aircraft carrier by default.");
        return aircraftCarrier;
    }


    public void printArray() {
        int num1 = 0;
        int num2 = 0;
        System.out.println("\n");
        for(int i = 0; i < 10; i++) {
            System.out.println("");
            for(int j = 0; j < 10; j++) System.out.print(humanArr[num1++]);
            System.out.print("        ");
            for(int j = 0; j < 10; j++) System.out.print(compArr[num2++]);
        }
        System.out.println("\n");
    }

    public static void main(String[] args) {
        BattleshipMain newGame = new BattleshipMain();
    }
}

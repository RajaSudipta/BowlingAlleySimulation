/*
 * PinSetterView/.java
 *
 * Version:
 *   $Id$
 *
 * Revision:
 *   $Log$
 */

/**
 *  constructs a prototype PinSetter GUI
 *
 */

import java.awt.*;
import javax.swing.*;
import java.util.Vector;


public class PinSetterView implements PinsetterObserver {


    private Vector pinVect = new Vector ( );
    private JPanel firstRoll;
    private JPanel secondRoll;

    /**
     * Constructs a Pin Setter GUI displaying which roll it is with
     * yellow boxes along the top (1 box for first roll, 2 boxes for second)
     * and displays the pins as numbers in this format:
     *
     *                7   8   9   10
     *                  4   5   6
     *                    2   3
     *                      1
     *
     */
    

	private JFrame frame;
    
	private void printPinSetterRows(JPanel pins) {
		
		JPanel panels[] = new JPanel[10];
		JLabel labels[] = new JLabel[10];
		
		for(int i=0; i<10; i++) {
			panels[i] = new JPanel();
		}
		
		for(int i=0; i<10; i++) {
			String lb = (i+1) + "";
			labels[i] = new JLabel(lb);
		}
		
		for(int i=0; i<10; i++){
            panels[i].add(labels[i]);
        }
		
		//This Vector will keep references to the pin labels to show which ones have fallen.
	
	    for(int i=0; i<10; i++){
	        pinVect.add(labels[i]);
	    }
		
		//******************************Fourth Row**************
		
		for(int i=7; i<=10; i++) {
			pins.add(panels[i-1]);
			pins.add(new JPanel());
		}
		
		//*****************************Third Row***********
		
		for(int i=4; i<=6; i++) {
			pins.add(panels[i-1]);
			pins.add(new JPanel());
		}
		
		//*****************************Second Row**************
		
		for(int i=1; i<=7; i++) {
			if(i==3) {
				pins.add(panels[1]);
			} else if(i==5) {
				pins.add(panels[2]);
			} else {
				pins.add(new JPanel());
			}
		}
		
		//******************************First Row*****************
		
		for(int i=1; i<=7; i++) {
			if(i==4) {
				pins.add(panels[0]);
			} else {
				pins.add ( new JPanel());
			}
		}		
	}
	
    public PinSetterView ( int laneNum ) {
	
		frame = new JFrame ( "Lane " + laneNum + ":" );
		
		Container cpanel = frame.getContentPane ( );
		
		JPanel pins = new JPanel ( );
		
		pins.setLayout ( new GridLayout ( 4, 7 ) );
		
		//********************Top of GUI indicates first or second roll
		
		JPanel top = new JPanel ( );
		
		firstRoll = new JPanel ( );
		firstRoll.setBackground( Color.yellow );
		
		secondRoll = new JPanel ( );
		secondRoll.setBackground ( Color.black );
		
		top.add ( firstRoll, BorderLayout.WEST );
		
		top.add ( secondRoll, BorderLayout.EAST );
		
		//******************************************************************
		
		//**********************Grid of the pins**************************
		
		printPinSetterRows(pins);
		
		top.setBackground ( Color.black );
		
		cpanel.add ( top, BorderLayout.NORTH );
		
		pins.setBackground ( Color.black );
		pins.setForeground ( Color.yellow );
		
		cpanel.add ( pins, BorderLayout.CENTER );
		
		frame.pack();
		
	//	frame.show();
    }
    
    
    /**
     * This method receives a pinsetter event.  The event is the current
     * state of the PinSetter and the method changes how the GUI looks
     * accordingly.  When pins are "knocked down" the corresponding label
     * is grayed out.  When it is the second roll, it is indicated by the
     * appearance of a second yellow box at the top.
     *
     * @param e    The state of the pinsetter is sent in this event.
     */
    

    public void receivePinsetterEvent(PinsetterEvent pe){
	if ( !(pe.isFoulCommited()) ) {
	    	JLabel tempPin = new JLabel ( );
	    	for ( int c = 0; c < 10; c++ ) {
				boolean pin = pe.pinKnockedDown ( c );
				tempPin = (JLabel)pinVect.get ( c );
				if ( pin ) {
		    		tempPin.setForeground ( Color.lightGray );
				}
	    	}
    	}
		if ( pe.getThrowNumber() == 1 ) {
	   		 secondRoll.setBackground ( Color.yellow );
		}
	if ( pe.pinsDownOnThisThrow() == -1) {
		for ( int i = 0; i != 10; i++){
			((JLabel)pinVect.get(i)).setForeground(Color.black);
		}
		secondRoll.setBackground( Color.black);
	}
    }
    
    public void show() {
    	frame.show();
    }

    public void hide() {
    	frame.hide();
    }
    
    public static void main ( String args [ ] ) {
		PinSetterView pg = new PinSetterView ( 1 );
    }
    
}

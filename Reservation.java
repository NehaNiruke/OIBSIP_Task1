package Internship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Reservation extends JFrame {
    private static boolean[] seats = new boolean[25];

    public Reservation() {
        super("Seat Reservation System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 1));

        JButton viewSeatMapButton = new JButton("View Seat Map");
        JButton reserveSeatButton = new JButton("Reserve Seat");
        JButton cancelReservationButton = new JButton("Cancel Reservation");
        JButton exitButton = new JButton("Exit");

        viewSeatMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewSeatMap();
            }
        });

        reserveSeatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reserveSeat();
            }
        });

        cancelReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelReservation();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel.add(viewSeatMapButton);
        panel.add(reserveSeatButton);
        panel.add(cancelReservationButton);
        panel.add(exitButton);

        add(panel);
        setVisible(true);
    }

    private void viewSeatMap() {
        StringBuilder seatMap = new StringBuilder("\nCURRENT SEAT MAP:\n");
        for (int i = 0; i < seats.length; i++) {
            if (seats[i]) {
                seatMap.append("X ");
            } else {
                seatMap.append(i + 1).append(" ");
            }
        }
        JOptionPane.showMessageDialog(this, seatMap.toString());
    }

    private void reserveSeat() {
        String seatNumberString = JOptionPane.showInputDialog("Enter seat number between 1 to 25:");
        try {
            int seatNumber = Integer.parseInt(seatNumberString);
            if (seatNumber < 1 || seatNumber > 25) {
                JOptionPane.showMessageDialog(this, "Invalid seat number!");
            } else if (seats[seatNumber - 1]) {
                JOptionPane.showMessageDialog(this, "Seat already reserved!");
            } else {
                seats[seatNumber - 1] = true;
                JOptionPane.showMessageDialog(this, "Seat reserved!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.");
        }
    }

    private void cancelReservation() {
        String seatNumberString = JOptionPane.showInputDialog("Enter seat number between 1 to 25:");
        try {
            int seatNumber = Integer.parseInt(seatNumberString);
            if (seatNumber < 1 || seatNumber > 25) {
                JOptionPane.showMessageDialog(this, "Invalid seat number!");
            } else if (!seats[seatNumber - 1]) {
                JOptionPane.showMessageDialog(this, "Seat not reserved!");
            } else {
                seats[seatNumber - 1] = false;
                JOptionPane.showMessageDialog(this, "Reservation cancelled!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Reservation();
            }
        });
    }
}

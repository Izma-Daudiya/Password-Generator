
// import java.util.*;
import java.security.SecureRandom;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordGenerator {

  private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
  private static final String DIGITS = "0123456789";
  private static final String SPECIAL_CHARACTERS = "!@#$%^&*()_-+=[]{}|;:,.<>?";
  private static final String ALL_CHARACTERS = UPPERCASE + LOWERCASE + DIGITS + SPECIAL_CHARACTERS;

  private static final SecureRandom random = new SecureRandom();

  public static String generatePassword(int length) {

    if (length < 1) {
      throw new IllegalArgumentException("Password length must be at least 1");
    }
    StringBuilder password = new StringBuilder(length);

    password.append(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
    password.append(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
    password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
    password.append(SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length())));

    for (int i = 4; i < length; i++) {
      password.append(ALL_CHARACTERS.charAt(random.nextInt(ALL_CHARACTERS.length())));
    }
    return shuffleString(password.toString());
  }

  private static String shuffleString(String input) {
    char characters[] = input.toCharArray();
    for (int i = 0; i < characters.length; i++) {
      int randomIndex = random.nextInt(characters.length);
      char temp = characters[i];
      characters[i] = characters[randomIndex];
      characters[randomIndex] = temp;
    }
    return new String(characters);
  }

  public static void main(String[] args) {

    // Create Frame
    JFrame frame = new JFrame("Password Generator");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 200);
    frame.setLayout(new GridLayout(4, 1));

    // Create Font
    Font font = new Font("Arial", Font.BOLD, 20);

    // Create Components
    JLabel lengthLabel = new JLabel("Enter Password Length: ");
    lengthLabel.setFont(font);
    JTextField lengthField = new JTextField();
    lengthField.setFont(font);
    JButton generateButton = new JButton("Generate Password");
    generateButton.setFont(font);
    JTextField passworField = new JTextField();
    passworField.setFont(font);
    passworField.setEditable(false);

    // Add components to frame
    frame.add(lengthLabel);
    frame.add(lengthField);
    frame.add(generateButton);
    frame.add(passworField);

    generateButton.addActionListener(new ActionListener() {
     
      public void actionPerformed(ActionEvent e) {
        try {
          int length = Integer.parseInt(lengthField.getText());
          String password = generatePassword(length);
          passworField.setText(password);
        } catch (NumberFormatException ex) {
          JOptionPane.showMessageDialog(frame, "Please enter a valid number", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
          JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    frame.setVisible(true);

    // Scanner sc = new Scanner(System.in);System.out.println("Enter length of your
    // password: ");
    // int passwordLength = sc.nextInt();

    // if(passwordLength<1)
    // {
    // throw new IllegalArgumentException("Password Length must be at least 1");
    // }else
    // {
    // // String password = generatePassword(passwordLength);
    // System.out.println("Generated Password: " + password);
    // }
  }
}

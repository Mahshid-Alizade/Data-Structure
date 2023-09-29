import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.Externalizable;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Window.Type;
import java.awt.Color;
import java.awt.SystemColor;

public class mainClass extends JFrame {

	private JPanel contentPane;
	public static JTextArea answerArea;// text area of result
	private JTextArea enteryArea;// text area of Entery
	private JButton btndefine;
	private JButton btnnew;
	public static JLabel errorLabel;// the label that show error
	static HashMap map = new HashMap();
	static boolean start = false;// when we press #new it will be TRUE
	static mainClass frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new mainClass();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mainClass() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 543, 383);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(238, 130, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 47, 183, 123);
		contentPane.add(scrollPane);

		enteryArea = new JTextArea();
		enteryArea.setToolTipText("you ca use of : \"#new\" , \"#define\" , \"#print\" , \"#end\"");
		enteryArea.setFont(new Font("Agency FB", Font.BOLD, 22));
		enteryArea.setBackground(SystemColor.controlHighlight);
		scrollPane.setViewportView(enteryArea);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 206, 183, 78);
		contentPane.add(scrollPane_1);

		answerArea = new JTextArea();
		answerArea.setFont(new Font("Agency FB", Font.BOLD, 16));
		answerArea.setBackground(SystemColor.controlHighlight);
		scrollPane_1.setViewportView(answerArea);

		JButton btnCOM = new JButton("C O M P U T E");
		btnCOM.setBackground(Color.CYAN);
		btnCOM.setFocusable(false);
		btnCOM.setFont(new Font("Agency FB", Font.BOLD, 21));
		btnCOM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (start) {

					String[] entery = enteryArea.getText().split("\n");

					for (int i = 0; i < entery.length; i++) {

						// when we enter #define
						if (entery[i].contains("#define")) {
							defineVaribleAndFunc(entery[i]);
						}
						// when we enter #print
						if (entery[i].contains("#print")) {

							String infix = extractionInfix(entery[i]);// get infix

							if (manageErrors(infix)) {
								mainClass.errorLabel.setForeground(Color.RED);
								mainClass.errorLabel.setText(">>err3 : wrong syntax");
								mainClass.answerArea.append(">>err3 : wrong syntax" + "\n");
								continue;
							}
//							if (!isFunc(infix)) {
//								String stringFormOfInfix = Tree.infixToPostfix(infix).getStringForm();
//								String answer = Tree.calPostfix(realPostfix(stringFormOfInfix));
//								if (answer.equals("Infinity ")) {
//									mainClass.errorLabel.setForeground(Color.RED);
//									mainClass.errorLabel.setText(">>err2 : Undefined exp");
//									mainClass.answerArea.append(">>err2 : Undefined exp" + "\n");
//								} else if (answer != null) {
//									System.out.println(answer);
//									answerArea.append(">>" + answer + "\n");
//									map.printMap();
//								}
//							} else {

								// extract function and
								String res = extractFuncAndcampute(infix);
								String answer = computeinfix(res);

								// manage error
								if (answer != null && answer.equals("Infinity ")) {
									mainClass.errorLabel.setForeground(Color.RED);
									mainClass.errorLabel.setText(">>err2 : Undefined value");
									mainClass.answerArea.append(">>err2 : Undefined value" + "\n");
								} else if (!answer.equals(null + " ")) {//IN KOSHT MANO -_-
									answerArea.append(">>" + answer + "\n");
									map.printMap();
								}
							}
//						}
					}
				}

			}

		});
		btnCOM.setBounds(215, 230, 122, 54);
		contentPane.add(btnCOM);

		btndefine = new JButton("# D e f i n e");
		btndefine.setFocusable(false);
		btndefine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (start) {
					enteryArea.append("#define ");
				}
			}
		});
		btndefine.setToolTipText("#define x = 5");
		btndefine.setBackground(Color.ORANGE);
		btndefine.setFont(new Font("Agency FB", Font.BOLD, 20));
		btndefine.setBounds(215, 68, 122, 43);
		contentPane.add(btndefine);

		// we can start to use of app
		btnnew = new JButton("# N e w");
		btnnew.setToolTipText("#new");
		btnnew.setFocusable(false);
		btnnew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				start = true;
				map.clear();
				errorLabel.setForeground(Color.YELLOW);
				enteryArea.setText("");
				answerArea.setText("");
				errorLabel.setText("let's Start");
			}
		});
		btnnew.setBackground(new Color(255, 0, 0));
		btnnew.setForeground(Color.BLACK);
		btnnew.setFont(new Font("Agency FB", Font.BOLD, 20));
		btnnew.setBounds(215, 14, 122, 43);
		contentPane.add(btnnew);

		JLabel lblENT = new JLabel("E n t e r  H e r e :");
		lblENT.setFont(new Font("Agency FB", Font.BOLD, 18));
		lblENT.setHorizontalAlignment(SwingConstants.LEFT);
		lblENT.setBounds(10, 0, 177, 43);
		contentPane.add(lblENT);

		JLabel lblANS = new JLabel("A n s w e r   I s  :");
		lblANS.setFont(new Font("Agency FB", Font.BOLD, 16));
		lblANS.setBounds(10, 172, 183, 33);
		contentPane.add(lblANS);

		JButton btnEN = new JButton("# E n d");
		btnEN.setFocusable(false);
		btnEN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (start) {
					frame.dispose();
				}
			}
		});
		btnEN.setToolTipText("#end");
		btnEN.setBackground(Color.GREEN);
		btnEN.setFont(new Font("Agency FB", Font.BOLD, 20));
		btnEN.setBounds(215, 176, 122, 43);
		contentPane.add(btnEN);

		JButton btnPR = new JButton("# P r i n t");
		btnPR.setFocusable(false);
		btnPR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (start) {
					enteryArea.append("#print ");
				}
			}
		});
		btnPR.setToolTipText("#print 12 + x");
		btnPR.setBackground(new Color(255, 255, 0));
		btnPR.setFont(new Font("Agency FB", Font.BOLD, 20));
		btnPR.setBounds(215, 122, 122, 43);
		contentPane.add(btnPR);

		errorLabel = new JLabel("");
		errorLabel.setForeground(Color.YELLOW);
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setFont(new Font("Agency FB", Font.BOLD, 23));
		errorLabel.setBounds(10, 295, 327, 38);
		contentPane.add(errorLabel);

		JButton btnX = new JButton("1");
		btnX.setBackground(new Color(211, 211, 211));
		btnX.setForeground(new Color(0, 0, 0));
		btnX.setFocusable(false);
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enteryArea.append("1");
			}
		});
		btnX.setFont(new Font("Agency FB", Font.BOLD, 14));
		btnX.setBounds(347, 13, 51, 44);
		contentPane.add(btnX);

		JButton btnY = new JButton("4");
		btnY.setBackground(new Color(211, 211, 211));
		btnY.setForeground(new Color(0, 0, 0));
		btnY.setFocusable(false);
		btnY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enteryArea.append("4");
			}
		});
		btnY.setFont(new Font("Agency FB", Font.BOLD, 14));
		btnY.setBounds(347, 68, 51, 44);
		contentPane.add(btnY);

		JButton button = new JButton("7");
		button.setBackground(new Color(211, 211, 211));
		button.setForeground(new Color(0, 0, 0));
		button.setFocusable(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enteryArea.append("7");
			}
		});
		button.setFont(new Font("Agency FB", Font.BOLD, 14));
		button.setBounds(347, 122, 51, 44);
		contentPane.add(button);

		JButton button_1 = new JButton("(");
		button_1.setBackground(new Color(211, 211, 211));
		button_1.setForeground(new Color(0, 0, 0));
		button_1.setFocusable(false);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enteryArea.append("(");
			}
		});
		button_1.setFont(new Font("Agency FB", Font.BOLD, 14));
		button_1.setBounds(347, 176, 51, 44);
		contentPane.add(button_1);

		JButton button_2 = new JButton("2");
		button_2.setBackground(new Color(211, 211, 211));
		button_2.setForeground(new Color(0, 0, 0));
		button_2.setFocusable(false);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enteryArea.append("2");
			}
		});
		button_2.setFont(new Font("Agency FB", Font.BOLD, 15));
		button_2.setBounds(405, 13, 51, 44);
		contentPane.add(button_2);

		JButton button_3 = new JButton("5");
		button_3.setBackground(new Color(211, 211, 211));
		button_3.setForeground(new Color(0, 0, 0));
		button_3.setFocusable(false);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enteryArea.append("5");
			}
		});
		button_3.setFont(new Font("Agency FB", Font.BOLD, 14));
		button_3.setBounds(405, 68, 51, 44);
		contentPane.add(button_3);

		JButton button_4 = new JButton("8");
		button_4.setBackground(new Color(211, 211, 211));
		button_4.setForeground(new Color(0, 0, 0));
		button_4.setFocusable(false);
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enteryArea.append("8");
			}
		});
		button_4.setFont(new Font("Agency FB", Font.BOLD, 14));
		button_4.setBounds(405, 122, 51, 44);
		contentPane.add(button_4);

		JButton button_5 = new JButton("0");
		button_5.setBackground(new Color(211, 211, 211));
		button_5.setForeground(new Color(0, 0, 0));
		button_5.setFocusable(false);
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enteryArea.append("0");
			}
		});
		button_5.setFont(new Font("Agency FB", Font.BOLD, 14));
		button_5.setBounds(405, 176, 51, 44);
		contentPane.add(button_5);

		JButton button_6 = new JButton("3");
		button_6.setBackground(new Color(211, 211, 211));
		button_6.setForeground(new Color(0, 0, 0));
		button_6.setFocusable(false);
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enteryArea.append("3");
			}
		});
		button_6.setFont(new Font("Agency FB", Font.BOLD, 14));
		button_6.setBounds(463, 13, 51, 44);
		contentPane.add(button_6);

		JButton button_7 = new JButton("6");
		button_7.setBackground(new Color(211, 211, 211));
		button_7.setForeground(new Color(0, 0, 0));
		button_7.setFocusable(false);
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enteryArea.append("6");
			}
		});
		button_7.setFont(new Font("Agency FB", Font.BOLD, 14));
		button_7.setBounds(463, 68, 51, 44);
		contentPane.add(button_7);

		JButton button_8 = new JButton("9");
		button_8.setBackground(new Color(211, 211, 211));
		button_8.setForeground(new Color(0, 0, 0));
		button_8.setFocusable(false);
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enteryArea.append("9");
			}
		});
		button_8.setFont(new Font("Agency FB", Font.BOLD, 14));
		button_8.setBounds(463, 122, 51, 44);
		contentPane.add(button_8);

		JButton button_9 = new JButton(")");
		button_9.setBackground(new Color(211, 211, 211));
		button_9.setForeground(new Color(0, 0, 0));
		button_9.setFocusable(false);
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enteryArea.append(")");
			}
		});
		button_9.setFont(new Font("Agency FB", Font.BOLD, 14));
		button_9.setBounds(463, 176, 51, 44);
		contentPane.add(button_9);

		JButton btnX_1 = new JButton("X");
		btnX_1.setBackground(new Color(211, 211, 211));
		btnX_1.setForeground(new Color(0, 0, 0));
		btnX_1.setFocusable(false);
		btnX_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enteryArea.append("x");
			}
		});
		btnX_1.setFont(new Font("Agency FB", Font.BOLD, 14));
		btnX_1.setBounds(347, 230, 51, 44);
		contentPane.add(btnX_1);

		JButton btnFx = new JButton("f(x)");
		btnFx.setBackground(new Color(211, 211, 211));
		btnFx.setForeground(new Color(0, 0, 0));
		btnFx.setFocusable(false);
		btnFx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enteryArea.append("f(x)");
			}
		});
		btnFx.setFont(new Font("Agency FB", Font.BOLD, 12));
		btnFx.setBounds(347, 284, 51, 44);
		contentPane.add(btnFx);

		JButton btnY_1 = new JButton("Y");
		btnY_1.setBackground(new Color(211, 211, 211));
		btnY_1.setForeground(new Color(0, 0, 0));
		btnY_1.setFocusable(false);
		btnY_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enteryArea.append("y");
			}
		});
		btnY_1.setFont(new Font("Agency FB", Font.BOLD, 14));
		btnY_1.setBounds(405, 230, 51, 44);
		contentPane.add(btnY_1);

		JButton btnGx = new JButton("g(x)");
		btnGx.setBackground(new Color(211, 211, 211));
		btnGx.setForeground(new Color(0, 0, 0));
		btnGx.setFocusable(false);
		btnGx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enteryArea.append("g(x)");
			}
		});
		btnGx.setFont(new Font("Agency FB", Font.BOLD, 10));
		btnGx.setBounds(405, 284, 51, 44);
		contentPane.add(btnGx);

		JButton btnZ = new JButton("Z");
		btnZ.setBackground(new Color(211, 211, 211));
		btnZ.setForeground(new Color(0, 0, 0));
		btnZ.setFocusable(false);
		btnZ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enteryArea.append("z");
			}
		});
		btnZ.setFont(new Font("Agency FB", Font.BOLD, 14));
		btnZ.setBounds(463, 230, 51, 44);
		contentPane.add(btnZ);

		JButton btnFgx = new JButton("=");
		btnFgx.setBackground(new Color(211, 211, 211));
		btnFgx.setForeground(new Color(0, 0, 0));
		btnFgx.setFocusable(false);
		btnFgx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enteryArea.append("=");
			}
		});
		btnFgx.setFont(new Font("Agency FB", Font.BOLD, 14));
		btnFgx.setBounds(463, 284, 51, 44);
		contentPane.add(btnFgx);
	}

	// put value and function in HashMap
	private void defineVaribleAndFunc(String s) {

		String[] s2 = s.split("#define ");
		if (s2[1].split("=").length == 2) {
			String key = s2[1].split("=")[0].trim();
			String value = "";

			if (isFunc(key))// if it is function's value
				value = s2[1].split("=")[1].trim();
			else// if it is function's key
				value = computeinfix(s2[1].split("=")[1].trim()).trim();

			map.put(key, value);

		} else {
			errorLabel.setForeground(Color.RED);
			errorLabel.setText(">>err1 : wrong exp");
			answerArea.append(">>err1 : wrong exp" + "\n");
		}

		map.printMap();
	}

	// extract infix
	private String extractionInfix(String s) {

		String[] s2 = s.split("#print ");
		String infix = s2[1].trim();

		return infix;
	}

	// replace letter with the value of postfix
	public static LinkedList realPostfix(String s) {

		String[] array = s.split(" ");

		for (int i = 0; i < array.length; i++)
			if (map.containsKey(array[i]))
				array[i] = (String) map.get(array[i]);

		String result = "";
		for (int i = 0; i < array.length; i++)
			result += array[i] + " ";

		return LinkedList.stringToLinkedList(result);

	}

	// return the entery is function or not
	private static boolean isFunc(String s) {
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == '(' && Tree.isOprand(s.charAt(i - 1))) {
				return true;
			}
		}
		return false;
	}

	// replace letter with number
	public static String replaceLetterWithNum(String s, char ch, String num) {

		String[] array = s.split("");
		String result = "";
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(ch + ""))
				array[i] = num + "";
		}
		for (int i = 0; i < array.length; i++) {
			result += array[i];
		}

		return result;
	}

	// get function and compute it
	private String computeFunc(String tmp) {

		String result = "";
		String func = tmp.charAt(0) + "";

		if (isFunc(tmp.substring(2))) {
			String infix = tmp.substring(2, tmp.length() - 1);
			String res = extractFuncAndcampute(infix);
			tmp = tmp.substring(0, 2) + res.trim() + ")";
		}
		if (map.containsFunc(func)) {
			System.out.println("tmp is : " + tmp);
			String[] input = tmp.split("\\(", 2)[1].substring(0, tmp.split("\\(", 2)[1].length() - 1).split(",");
			String[] variable = ((String) map.getCompleteKeyFunc(func)).split("\\(")[1].split("\\)")[0].split(",");

			for (int i = 0; i < input.length; i++) {
				if (isFunc(input[i])) {
					input[i] = computeFunc(input[i]).trim();
				} else
					input[i] = computeinfix(input[i]).trim();
			}
			String funcValue = (String) map.getFunc(func);
			for (int i = 0; i < input.length; i++) {
				try {
					funcValue = replaceLetterWithNum(funcValue, variable[i].charAt(0), input[i]);
					System.out.println(funcValue);
				} catch (IndexOutOfBoundsException e) {
					mainClass.errorLabel.setForeground(Color.RED);
					mainClass.errorLabel.setText(">>err3 : wrong syntax");
					mainClass.answerArea.append(">>err3 : wrong syntax" + "\n");
					return null;
				}
			}
			result = computeinfix(funcValue);
			if (result == null)
				return null;
		}

		return result.trim();
	}

	// compute the result of infix
	private String computeinfix(String infix) {
		String stringFormOfInfix = Tree.infixToPostfix(infix).getStringForm();
		String answer = Tree.calPostfix(realPostfix(stringFormOfInfix));
		return answer;
	}

	// extract function and compute it
	private String extractFuncAndcampute(String infix) {
		int bracket = 0;// count bracket to extract function
		String tmp = "";
		String res = "";

		for (int j = 0; j < infix.length(); j++) {
			if (j < infix.length() - 1 && infix.charAt(j + 1) == '(' && Tree.isOprand(infix.charAt(j))) {
				bracket++;
				tmp += infix.charAt(j);
				tmp += infix.charAt(j + 1);
				j++;
				while (j < infix.length() && bracket != 0) {
					j++;
					if (j < infix.length() && infix.charAt(j) == '(')
						bracket++;
					else if (j < infix.length() && infix.charAt(j) == ')')
						bracket--;

					if (j < infix.length() && infix.charAt(j) != ' ')
						tmp += infix.charAt(j);
				}
				res += computeFunc(tmp);
				tmp = "";
			} else {
				res += infix.charAt(j);
			}
		}

		return res;
	}

	public static boolean manageErrors(String entery) {
		int bracket = 0;

		for (int i = 0; i < entery.length(); i++) {
			if (entery.charAt(i) == '(')
				bracket++;
			else if (entery.charAt(i) == ')')
				bracket--;
		}

		if (bracket == 0)
			return false;// no error
		else
			return true;
	}
}

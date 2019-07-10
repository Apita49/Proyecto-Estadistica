package proyecto;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class UI {
	Scanner sc = new Scanner(System.in);
	private int serie;
	private int dimension;
	private Funciones f = new Funciones();

	public void start() {
		askSerie();
		askDimension();
		f.setSerDim(serie, dimension);
		askData();
		askOperation();
	}

	private void askOperation() {
		int op;
		boolean fallo = false;
		do {
			fallo = false;
			System.out
					.println("Escoja la operacion que desea realizar\n1.Media Aritmetica\n2.Media Geometrica\n3.Media Armonica\n4.Mediana\n5.Moda\n6.Desviacion Media\n7.Varianza\n8.Desviacion Tipica\n9.Cuartiles\n10.Deciles\n11.Centiles\n12.Simetria\n13.Curtosis\n14.Amplitud Cuartilica\n15.Desvio Cuartilico\n16.Amplitud Centilica");
			if (serie == 1 && dimension > 1) {
				System.out
						.println("17.Ajuste de Curvas\n18.Mostrar Tabla\n0.Reiniciar");
				op = sc.nextInt();
				if (op > 18 || op < 0) {
					System.out
							.println("Usted escogio una opcion incorrecta, vuelva a intentar");
					fallo = true;
				}
			} else {
				System.out.println("17.Mostrar Tabla");
				System.out.println("0.Reiniciar");
				op = sc.nextInt();
				if (op > 17 || op < 0) {
					System.out
							.println("Usted escogio una opcion incorrecta, vuelva a intentar");
					fallo = true;
				}
			}
			int number = 0;
			boolean x = false;
			boolean inver = false;
			if (op == 11) {
				x = true;
				do {
					System.out.println("Que centil quiere calular? (1-99)");
					number = sc.nextInt();
					if (number < 1 || number > 99) {
						System.out
								.println("Ingreso un numero de centil invalido, vuelva a ingresarlo");
					}
				} while (number < 1 || number > 99);
			} else if (op == 10) {
				x = true;
				do {
					System.out.println("Que decil quiere calular? (1-9)");
					number = sc.nextInt();
					if (number < 1 || number > 9) {
						System.out
								.println("Ingreso un numero de decil invalido, vuelva a ingresarlo");
					}
				} while (number < 1 || number > 9);
			} else if (op == 9) {
				x = true;
				do {
					System.out.println("Que cuartil quiere calular? (1-3)");
					number = sc.nextInt();
					if (number < 1 || number > 3) {
						System.out
								.println("Ingreso un numero de cuartil invalido, vuelva a ingresarlo");
					}
				} while (number < 1 || number > 3);
			} else if (op == 17 && dimension > 1) {
				x = true;
				do {
					System.out
							.println("Que ajuste desea realizar?\n1.Lineal\n2.Exponencial\n3.Potencial");
					number = sc.nextInt();
					if (number < 1 || number > 3) {
						System.out
								.println("Ingreso un numero invalido, vuelva a ingresarlo");
					} else {
						if (number == 1) {
							System.out
									.println("El ajuste se hara de acuerdo a y = ax + b");
						} else if (number == 2) {
							System.out
									.println("El ajuste se hara de acuerdo a y = ab^x");
						} else if (number == 3) {
							System.out
									.println("El ajuste se hara de acuerdo a y = ax^b");
						}
						f.setQDC(number);
						System.out
								.println("El coeficiente de relacion es "
										+ f.coefRelac()
										+ "\nDesea proseguir?\n1.Si\n2.Realizar otro ajuste\n3.Invertir");
						int sni;
						do {
							sni = sc.nextInt();
							if (sni > 3 || sni < 1) {
								System.out
										.println("Ingreso una opcion invalida, vuelva a intentar");
							}
						} while (sni > 3 || sni < 1);
						if (sni == 1) {
							x = false;
						} else if (sni == 3) {
							f.setInverse(true);
							inver = true;
							x = false;
						}
					}
				} while (number < 1 || number > 3 || x);
			}
			if (x) {
				f.setQDC(number);
			}
			if (dimension == 2 && op != 18 && op != 17 && op != 0) {
				int xoy;
				System.out.println("Respecto a que variable? \n1.y\n2.x");
				do {
					xoy = sc.nextInt();
					if (xoy > 2 || xoy < 1) {
						System.out
								.println("Opcion invalida, vuelva a intentarlo");
					}
				} while (xoy > 2 || xoy < 1);
				f.setXOrY(xoy - 1);
			}
			if (op == 1) {
				double res = f.doFunction(op);
				System.out.print("La media aritmetica es ");
				System.out.println(res);
			} else if (op == 2) {
				double res = f.doFunction(op);
				System.out.print("La media geometrica es ");
				System.out.println(res);

			} else if (op == 3) {
				double res = f.doFunction(op);
				System.out.print("La media armonica es ");
				System.out.println(res);

			} else if (op == 4) {
				double res = f.doFunction(op);
				System.out.print("La mediana es ");
				System.out.println(res);
			} else if (op == 5) {
				double res = f.doFunction(op);
				if (serie == 1) {
					System.out.println("No existe moda");
				} else {
					System.out.print("La moda es ");
					System.out.println(res);
				}
			} else if (op == 6) {
				double res = f.doFunction(op);
				System.out.print("La desviacion media es ");
				System.out.println(res);
			} else if (op == 7) {
				double res = f.doFunction(op);
				System.out.print("La varianza es ");
				System.out.println(res);
			} else if (op == 8) {
				double res = f.doFunction(op);
				System.out.print("La desviacion tipica es ");
				System.out.println(res);
			} else if (op == 9) {
				double res = f.doFunction(op);
				System.out.print("El Cuartil " + number + " es igual a ");
				System.out.println(res);
			} else if (op == 10) {
				double res = f.doFunction(op);
				System.out.print("El Decil " + number + " es igual a ");
				System.out.println(res);
			} else if (op == 11) {
				double res = f.doFunction(op);
				System.out.print("El Centil " + number + " es igual a ");
				System.out.println(res);
			} else if (op == 12) {
				double res = f.doFunction(op);
				System.out.print("La simetria es ");
				System.out.println(res);
			} else if (op == 13) {
				double res = f.doFunction(op);
				System.out.print("La curtosis es ");
				System.out.println(res);
			} else if (op == 14) {
				double res = f.doFunction(op);
				System.out.print("La amplitud cuartilica es ");
				System.out.println(res);
			} else if (op == 15) {
				double res = f.doFunction(op);
				System.out.print("El desvio cuartilica es ");
				System.out.println(res);
			} else if (op == 16) {
				double res = f.doFunction(op);
				System.out.print("La amplitud centilica es ");
				System.out.println(res);
			} else if (op == 17) {
				if (serie == 1 && dimension > 1) {
					double res[] = f.ajusteCurvas();
					System.out.println("El ajuste de curvas es ");
					if (number == 1) {
						if (!inver) {
							System.out.println("y = " + res[0] + "x + "
									+ res[1]);
							System.out.println("Introducir un dato para x");
							double data = sc.nextDouble();
							System.out.println("y = "
									+ (res[0] * data + res[1]));
						} else {
							System.out
									.println("x = " + res[0] + "y +" + res[1]);
							System.out.println("Introducir un dato para y");
							double data = sc.nextDouble();
							System.out.println("x = "
									+ (res[0] * data + res[1]));
						}
					} else if (number == 2) {
						if (!inver) {
							System.out.println("y = " + res[0] + "*" + res[1]
									+ "^x");
							System.out.println("Introducir un dato para x");
							double data = sc.nextDouble();
							System.out.println("y = "
									+ (res[0] * Math.pow(res[1], data)));
						} else {
							System.out.println("x = " + res[0] + "*" + res[1]
									+ "^y");
							System.out.println("Introducir un dato para y");
							double data = sc.nextDouble();
							System.out.println("x = "
									+ (res[0] * Math.pow(res[1], data)));
						}
					} else if (number == 3) {
						if (!inver) {
							System.out.println("y = " + res[0] + "x^" + res[1]);
							System.out.println("Introducir un dato para x");
							double data = sc.nextDouble();
							System.out.println("y = "
									+ (res[0] * Math.pow(data, res[1])));
						} else {
							System.out.println("x = " + res[0] + "y^" + res[1]);
							System.out.println("Introducir un dato para y");
							double data = sc.nextDouble();
							System.out.println("x = "
									+ (res[0] * Math.pow(data, res[1])));
						}
					}
				} else if (serie == 1 && dimension == 1) {
					showTable1(f.getTable1());
				} else {
					showTable(f.getTable());
				}
			} else if (op == 18) {
				@SuppressWarnings("unused")
				double res = f.doFunction(op);
				if (serie == 1 && dimension == 2) {
					showTable(f.getTable());
				}
			}
		} while (fallo || op != 0);
		if (op == 0) {
			f.restart();
			start();
			serie = 0;
			dimension = 0;
		}
	}

	public void showTable(double[][] table) {
		if (serie == 1) {
			System.out.println("Xi  Yi");
		} else if (serie == 2) {
			System.out.println("Xi  ni  Ni  fi  Fi");
		} else if (serie == 3) {
			System.out.println("Li-1  Li  ni  Ni  fi  Fi  xi  hi  ai");
		}
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[0].length; j++) {
				if (serie == 1 && dimension == 2 && j == 2) {
					break;
				}
				System.out.print(" " + table[i][j] + "  ");
			}
			System.out.println();
		}
	}

	public void showTable1(double[] table) {
		System.out.println("Xi");
		for (int i = 0; i < table.length; i++) {
			System.out.println(table[i]);
		}
	}

	public void askSerie() {
		do {
			System.out
					.println("Escoja la serie que desea utilizar\n1.Primera serie\n2.Segunda serie\n3.Tercera serie");
			serie = sc.nextInt();
			if (serie > 3 || serie < 1) {
				System.out.println("Opcion incorrecta, escoja de nuevo");
			}
		} while (serie > 3 || serie < 1);
	}

	public void askDimension() {
		if (serie == 1) {
			do {
				System.out.println("Es bidimensional?\n1.No\n2.Si");
				dimension = sc.nextInt();
				if (dimension > 2 || dimension < 1) {
					System.out.println("Opcion incorrecta, escoja de nuevo");
				}
			} while (dimension > 2 || dimension < 1);
		} else {
			dimension = 1;
		}
	}

	private void askData() {
		if (serie == 1) {
			if (dimension == 1) {
				int datos;
				do {
					System.out.println("Cuantos datos tiene?");
					datos = sc.nextInt();
					if (datos < 1) {
						System.out
								.println("No puedo trabajar con cero o menos datos");
					}
				} while (datos < 1);
				f.setFunctionSize(datos, 0);
				List<Double> list = new LinkedList<>();
				for (int i = 0; i < datos; i++) {
					System.out.println("Introduzca el dato " + (i + 1));
					double x;
					do {
						x = sc.nextDouble();
						if (list.contains(x)) {
							System.out
									.println("Introdujo un dato que ya tenia antes, introduzca otro por favor");
						}
					} while (list.contains(x));
					list.add(x);
					f.setData(i, 0, x);
				}
			} else {
				int datos;
				do {
					System.out.println("Cuantos pares de datos tiene?");
					datos = sc.nextInt();
					if (datos < 1) {
						System.out
								.println("No puedo trabajar con cero o menos pares de datos");
					}
				} while (datos < 1);
				f.setFunctionSize(datos, 4);
				List<Double> listX = new LinkedList<>();
				List<Double> listY = new LinkedList<>();
				for (int i = 0; i < datos; i++) {
					System.out.println("Introduzca el dato Y" + (i + 1));
					double x;
					do {
						x = sc.nextDouble();
						if (listX.contains(x)) {
							System.out
									.println("Introdujo un dato que ya tenia antes, introduzca otro por favor");
						}
					} while (listX.contains(x));
					listX.add(x);
					System.out.println("Introduzca el dato X" + (i + 1));
					double y;
					do {
						y = sc.nextInt();
						if (listY.contains(y)) {
							System.out
									.println("Introdujo un dato que ya tenia antes, introduzca otro por favor");
						}
					} while (listY.contains(y));
					listY.add(y);
					f.setData(i, 0, x);
					f.setData(i, 1, y);
					f.setData(i, 2, Math.log(x));
					f.setData(i, 3, Math.log(y));
				}

			}
		} else if (serie == 2) {
			int datos;
			do {
				System.out.println("Cuantos datos tiene?");
				datos = sc.nextInt();
				if (datos < 1) {
					System.out
							.println("No puedo trabajar con cero o menos datos");
				}
			} while (datos < 1);
			f.setFunctionSize(datos, 5);
			List<Double> list = new LinkedList<>();
			for (int i = 0; i < datos; i++) {
				System.out.println("Introduzca el dato " + (i + 1));
				double x;
				do {
					x = sc.nextDouble();
					if (list.contains(x)) {
						System.out
								.println("Introdujo un dato que ya tenia antes, introduzca otro por favor");
					}
				} while (list.contains(x));
				list.add(x);
				System.out.println("Introduzca la frecuencia del dato "
						+ (i + 1));
				int fr;
				do {
					fr = sc.nextInt();
					if (fr < 0) {
						System.out
								.println("No existen frecuencias negativas, vuelva a introducir la frecuencia");
					}
				} while (fr < 0);
				f.setData(i, 0, x);
				f.setData(i, 1, fr);
			}
		} else {
			int inter;
			do {
				System.out.println("Cuantos intervalos tiene?");
				inter = sc.nextInt();
				if (inter < 1) {
					System.out
							.println("No puedo trabajar con cero o menos intervalos");
				}
			} while (inter < 1);
			f.setFunctionSize(inter, 9);
			List<Double> list = new LinkedList<>();
			double li = 0;
			double l2 = 0;
			for (int i = 0; i < inter; i++) {
				if (i == 0) {
					System.out
							.println("Introduzca el limite inferior del intervalo "
									+ (i + 1));
					do {
						li = sc.nextDouble();
						if (list.contains(li)) {
							System.out
									.println("Introdujo un dato que ya tenia antes, introduzca otro por favor");
						}
					} while (list.contains(li));
					list.add(li);
				}
				System.out
						.println("Introduzca el limite superior del intervalo "
								+ (i + 1));
				do {
					l2 = sc.nextDouble();
					if (list.contains(l2)) {
						System.out
								.println("Introdujo un dato que ya tenia antes, introduzca otro por favor");
					}
				} while (list.contains(l2));
				list.add(l2);
				System.out.println("Introduzca la frecuencia del intervalo "
						+ (i + 1));
				int fr;
				do {
					fr = sc.nextInt();
					if (fr < 0) {
						System.out
								.println("No existen frecuencias negativas, vuelva a introducir la frecuencia");
					}
				} while (fr < 0);
				f.setData(i, 0, li);
				f.setData(i, 1, l2);
				f.setData(i, 2, fr);
				li = l2;
			}
		}
	}
}

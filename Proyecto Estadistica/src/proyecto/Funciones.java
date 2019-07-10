package proyecto;

public class Funciones {

	private double[] funcion11;
	private double[][] funcion12;
	private double[][] funcion2;
	private double[][] funcion3;
	private double[] ajusteCurvas = new double[2];

	private int qdc = 0;
	private int serie;
	private int dimension;
	private int xOrY = 0;
	private boolean inverse = false;

	public void setXOrY(int n) {
		xOrY = n;
	}

	public void setInverse(boolean t) {
		inverse = t;
	}

	public void setSerDim(int s, int d) {
		serie = s;
		dimension = d;
	}

	public void setQDC(int number) {
		qdc = number;
	}

	public void setFunctionSize(int size1, int size2) {
		if (serie == 1) {
			if (dimension == 1) {
				funcion11 = new double[size1];
			} else {
				funcion12 = new double[size1][size2];
			}
		} else if (serie == 2) {
			funcion2 = new double[size1][size2];
		} else {
			funcion3 = new double[size1][size2];
		}
	}

	public void setData(int row, int col, double data) {
		if (serie == 1) {
			if (dimension == 1) {
				funcion11[row] = data;
			} else {
				funcion12[row][col] = data;
			}
		} else if (serie == 2) {
			funcion2[row][col] = data;
		} else {
			funcion3[row][col] = data;
		}
	}

	public double[] getTable1() {
		return funcion11.clone();
	}

	public double[][] getTable() {
		if (serie == 1) {
			return funcion12.clone();
		} else if (serie == 2) {
			return funcion2.clone();
		} else if (serie == 3) {
			return funcion3.clone();
		} else {
			return null;
		}
	}

	private void fillTable() {
		if (serie == 2) {
			double n = 0.0;
			for (int i = 0; i < funcion2.length; i++) {
				n += funcion2[i][1];
			}

			for (int i = 0; i < funcion2.length; i++) {
				if (i == 0) {
					funcion2[i][2] = funcion2[i][1];
				} else {
					funcion2[i][2] = funcion2[i - 1][2] + funcion2[i][1];
				}
			}

			for (int i = 0; i < funcion2.length; i++) {
				funcion2[i][3] = funcion2[i][1] / n;
			}

			for (int i = 0; i < funcion2.length; i++) {
				if (i == 0) {
					funcion2[i][4] = funcion2[i][3];
				} else {
					funcion2[i][4] = funcion2[i - 1][4] + funcion2[i][3];
				}
			}
		} else if (serie == 3) {
			double n = 0.0;
			for (int i = 0; i < funcion3.length; i++) {
				n += funcion3[i][2];
			}

			for (int i = 0; i < funcion3.length; i++) {
				if (i == 0) {
					funcion3[i][3] = funcion3[i][2];
				} else {
					funcion3[i][3] = funcion3[i - 1][3] + funcion3[i][2];
				}
			}

			for (int i = 0; i < funcion3.length; i++) {
				funcion3[i][4] = funcion3[i][2] / n;
			}

			for (int i = 0; i < funcion3.length; i++) {
				if (i == 0) {
					funcion3[i][5] = funcion3[i][4];
				} else {
					funcion3[i][5] = funcion3[i - 1][5] + funcion3[i][4];
				}
			}

			for (int i = 0; i < funcion3.length; i++) {
				funcion3[i][6] = (funcion3[i][1] + funcion3[i][0]) / 2;
			}

			for (int i = 0; i < funcion3.length; i++) {
				funcion3[i][7] = funcion3[i][1] - funcion3[i][0];
			}

			for (int i = 0; i < funcion3.length; i++) {
				funcion3[i][8] = funcion3[i][2] / funcion3[i][7];
			}

		}

	}

	public double doFunction(int op) {
		fillTable();
		if (op == 1) {
			return mediaAri();
		} else if (op == 2) {
			return mediaGeo();
		} else if (op == 3) {
			return mediaArm();
		} else if (op == 4) {
			return mediana();
		} else if (op == 5) {
			return moda();
		} else if (op == 6) {
			return desviacionMedia();
		} else if (op == 7) {
			return varianza();
		} else if (op == 8) {
			return desviacionEstandar();
		} else if (op == 9) {
			return cuartiles(qdc);
		} else if (op == 10) {
			return deciles(qdc);
		} else if (op == 11) {
			return centiles(qdc);
		} else if (op == 12) {
			return simetria();
		} else if (op == 13) {
			return curtosis();
		} else if (op == 14) {
			return amplitudCuartilica();
		} else if (op == 15) {
			return desvioCuartilico();
		} else if (op == 16) {
			return amplitudCentilica();
		}
		return 0;
	}

	private double amplitudCentilica() {
		return centiles(90) - centiles(10);
	}

	private double desvioCuartilico() {
		return amplitudCuartilica() / 2;
	}

	private double amplitudCuartilica() {
		return cuartiles(3) - cuartiles(1);
	}

	private double simetria() {
		if (serie == 1) {
			if (dimension == 1) {
				double s = 0;
				double mediaA = mediaAri();
				for (int i = 0; i < funcion11.length; i++) {
					s += Math.pow(funcion11[i] - mediaA, 3);
				}
				s /= (double) funcion11.length;
				s /= Math.pow(desviacionEstandar(), 3);
				return s;
			} else {
				double s = 0;
				double mediaA = mediaAri();
				for (int i = 0; i < funcion12.length; i++) {
					s += Math.pow(funcion12[i][xOrY] - mediaA, 3);
				}
				s /= (double) funcion12.length;
				s /= Math.pow(desviacionEstandar(), 3);
				return s;
			}
		} else if (serie == 2) {
			double s = 0;
			double n = 0;
			double mediaA = mediaAri();
			for (int i = 0; i < funcion2.length; i++) {
				s += Math.pow(funcion2[i][0] - mediaA, 3.0) * funcion2[i][1];
				n += funcion2[i][1];
			}
			s /= n;
			s /= Math.pow(desviacionEstandar(), 3);
			return s;
		} else {
			double s = 0;
			double n = 0;
			double mediaA = mediaAri();
			double[] x = marcaDeClase();
			for (int i = 0; i < funcion3.length; i++) {
				s += Math.pow(x[i] - mediaA, 3) * funcion3[i][2];
				n += funcion3[i][2];
			}
			s /= n;
			s /= Math.pow(desviacionEstandar(), 3);
			return s;
		}
	}

	private double curtosis() {
		if (serie == 1) {
			if (dimension == 1) {
				double k = 0;
				double mediaAr = mediaAri();
				double suma = 0;
				for (int i = 0; i < funcion11.length; i++) {
					suma += Math.pow((funcion11[i] - mediaAr), 4);
				}

				k = suma / funcion11.length;
				k /= Math.pow(desviacionEstandar(), 4);
				k -= 3;
				return k;
			} else {
				double k = 0;
				double mediaAr = mediaAri();
				double suma = 0;
				for (int i = 0; i < funcion12.length; i++) {
					suma += Math.pow((funcion12[i][xOrY] - mediaAr), 4);
				}

				k = suma / funcion12.length;
				k /= Math.pow(desviacionEstandar(), 4);
				k -= 3;
				return k;

			}
		} else if (serie == 2) {
			double k = 0;
			double mediaAr = mediaAri();
			double n = 0;
			double suma = 0;
			for (int i = 0; i < funcion2.length; i++) {
				suma += Math.pow((funcion2[i][0] - mediaAr), 4)
						* funcion2[i][1];
				n += funcion2[i][1];
			}
			k = suma / n;
			k /= Math.pow(desviacionEstandar(), 4);
			k -= 3;
			return k;
		} else {
			double k = 0;
			double mediaAr = mediaAri();
			double n = 0;
			double suma = 0;
			double[] x = marcaDeClase();
			for (int i = 0; i < funcion3.length; i++) {
				suma += Math.pow((x[i] - mediaAr), 4) * funcion3[i][2];
				n += funcion3[i][2];
			}

			k = suma / n;
			k /= Math.pow(desviacionEstandar(), 4);
			k -= 3;
			return k;
		}
	}

	private double centiles(int n) {
		if (serie == 1) {
			if (dimension == 1) {
				double x = ((double) (n * funcion11.length)) / (100.0);
				if (x == (int) x) {
					x = x - 1;
				}
				return funcion11[(int) x];
			} else {
				double x = ((double) (n * funcion12.length)) / (100.0);
				if (x == (int) x) {
					x = x - 1;
				}
				return funcion12[(int) x][xOrY];
			}
		} else if (serie == 2) {
			int index = 0;
			double[] faa = faa();
			double x = ((double) (n * faa[faa.length - 1])) / (100.0);
			for (int i = 0; i < faa.length; i++) {
				if (i != faa.length - 1) {
					if (x <= faa[i] && x < faa[i + 1]) {
						index = i;
						break;
					}
				} else {
					index = i;
					break;
				}
			}
			return funcion2[index][0];
		} else {
			int index = 0;
			double ret = 0;
			double[] faa = faa();
			double x = ((double) (n * faa[faa.length - 1])) / (100.0);
			for (int i = 0; i < faa.length; i++) {
				if (i != faa.length - 1) {
					if (x <= faa[i] && x < faa[i + 1]) {
						index = i;
						break;
					}
				} else {
					index = i;
					break;
				}
			}
			if (index == 0) {
				ret = funcion3[index][0]
						+ (((x) / (funcion3[index][2])) * (funcion3[index][1] - funcion3[index][0]));
			} else {
				ret = funcion3[index][0]
						+ (((x - faa[index - 1]) / (funcion3[index][2])) * (funcion3[index][1] - funcion3[index][0]));
			}
			return ret;
		}
	}

	private double deciles(int n) {
		if (serie == 1) {
			if (dimension == 1) {
				double x = ((double) (n * funcion11.length)) / (10.0);
				if (x == (int) x) {
					x = x - 1;
				}
				return funcion11[(int) x];
			} else {
				double x = ((double) (n * funcion12.length)) / (10.0);
				if (x == (int) x) {
					x = x - 1;
				}
				return funcion12[(int) x][xOrY];
			}
		} else if (serie == 2) {
			int index = 0;
			double[] faa = faa();
			double x = ((double) (n * faa[faa.length - 1])) / (10.0);
			for (int i = 0; i < faa.length; i++) {
				if (i != faa.length - 1) {
					if (x <= faa[i] && x < faa[i + 1]) {
						index = i;
						break;
					}
				} else {
					index = i;
					break;
				}
			}
			return funcion2[index][0];
		} else {
			int index = 0;
			double ret = 0;
			double[] faa = faa();
			double x = ((double) (n * faa[faa.length - 1])) / (10.0);
			for (int i = 0; i < faa.length; i++) {
				if (i != faa.length - 1) {
					if (x <= faa[i] && x < faa[i + 1]) {
						index = i;
						break;
					}
				} else {
					index = i;
					break;
				}
			}
			if (index == 0) {
				ret = funcion3[index][0]
						+ (((x) / (funcion3[index][2])) * (funcion3[index][1] - funcion3[index][0]));
			} else {
				ret = funcion3[index][0]
						+ (((x - faa[index - 1]) / (funcion3[index][2])) * (funcion3[index][1] - funcion3[index][0]));
			}
			return ret;
		}
	}

	private double cuartiles(int n) {
		if (serie == 1) {
			if (dimension == 1) {
				double x = ((double) (n * funcion11.length)) / (4.0);
				if (x == (int) x) {
					x = x - 1;
				}
				return funcion11[(int) x];
			} else {
				double x = ((double) (n * funcion12.length)) / (4.0);
				if (x == (int) x) {
					x = x - 1;
				}
				return funcion12[(int) x][xOrY];
			}
		} else if (serie == 2) {
			int index = 0;
			double[] faa = faa();
			double x = ((double) (n * faa[faa.length - 1])) / (4.0);
			for (int i = 0; i < faa.length; i++) {
				if (i != faa.length - 1) {
					if (x <= faa[i] && x < faa[i + 1]) {
						index = i;
						break;
					}
				} else {
					index = i;
					break;
				}
			}
			return funcion2[index][0];
		} else {
			int index = 0;
			double ret = 0;
			double[] faa = faa();
			double x = ((double) (n * faa[faa.length - 1])) / (4.0);
			for (int i = 0; i < faa.length; i++) {
				if (i != faa.length - 1) {
					if (x <= faa[i] && x < faa[i + 1]) {
						index = i;
						break;
					}
				} else {
					index = i;
					break;
				}
			}
			if (index == 0) {
				ret = funcion3[index][0]
						+ (((x) / (funcion3[index][2])) * (funcion3[index][1] - funcion3[index][0]));
			} else {
				ret = funcion3[index][0]
						+ (((x - faa[index - 1]) / (funcion3[index][2])) * (funcion3[index][1] - funcion3[index][0]));
			}
			return ret;
		}
	}

	private double desviacionEstandar() {
		if (serie == 1) {
			if (dimension == 1) {
				return Math.sqrt(varianza());
			} else {
				return Math.sqrt(varianza());
			}
		} else if (serie == 2) {
			return Math.sqrt(varianza());
		} else {
			return Math.sqrt(varianza());
		}
	}

	private double varianza() {
		if (serie == 1) {
			if (dimension == 1) {
				double v = 0;
				double mediaA = mediaAri();
				for (int i = 0; i < funcion11.length; i++) {
					v += Math.pow((funcion11[i] - mediaA), 2);
				}
				return v / funcion11.length;
			} else {
				double v = 0;
				double mediaA = mediaAri();
				for (int i = 0; i < funcion12.length; i++) {
					v += Math.pow((funcion12[i][xOrY] - mediaA), 2);
				}
				return v / funcion12.length;

			}
		} else if (serie == 2) {
			double v = 0;
			double mediaA = mediaAri();
			double n = 0;
			for (int i = 0; i < funcion2.length; i++) {
				v += Math.pow((funcion2[i][0] - mediaA), 2) * funcion2[i][1];
				n += funcion2[i][1];
			}

			return v / n;
		} else {
			double v = 0;
			double mediaA = mediaAri();
			double[] x = marcaDeClase();
			double n = 0;
			for (int i = 0; i < funcion3.length; i++) {
				v += Math.pow((x[i] - mediaA), 2) * funcion3[i][2];
				n += funcion3[i][2];
			}

			return v / n;
		}
	}

	private double desviacionMedia() {
		if (serie == 1) {
			if (dimension == 1) {
				double dM = 0;
				double mediaA = mediaAri();
				for (int i = 0; i < funcion11.length; i++) {
					dM += Math.abs(funcion11[i] - mediaA);
				}
				return dM / funcion11.length;
			} else {
				double dM = 0;
				double mediaA = mediaAri();
				for (int i = 0; i < funcion12.length; i++) {
					dM += Math.abs(funcion12[i][xOrY] - mediaA);
				}
				return dM / funcion12.length;
			}
		} else if (serie == 2) {
			double dM = 0;
			double mediaA = mediaAri();
			double n = 0;

			for (int i = 0; i < funcion2.length; i++) {
				dM += Math.abs(funcion2[i][0] - mediaA) * funcion2[i][1];
				n += funcion2[i][1];
			}

			return dM / n;
		} else {
			double dM = 0;
			double mediaA = mediaAri();
			double[] x = marcaDeClase();
			double n = 0;
			for (int i = 0; i < funcion3.length; i++) {
				dM += Math.abs(x[i] - mediaA) * funcion3[i][2];
				n += funcion3[i][2];
			}

			return dM / n;
		}
	}

	private double mediaAri() {
		if (serie == 1) {
			if (dimension == 1) {
				double suma = 0;
				for (int i = 0; i < funcion11.length; i++) {
					suma += funcion11[i];
				}
				return suma / funcion11.length;
			} else {
				double suma = 0;
				for (int i = 0; i < funcion12.length; i++) {
					suma += funcion12[i][xOrY];
				}
				return suma / funcion12.length;
			}
		} else if (serie == 2) {
			double suma = 0;
			double n = 0;
			for (int i = 0; i < funcion2.length; i++) {
				suma += funcion2[i][0] * funcion2[i][1];
				n += funcion2[i][1];
			}
			return suma / n;
		} else {
			double[] x = marcaDeClase();
			double suma = 0;
			double n = 0;
			for (int i = 0; i < funcion3.length; i++) {
				suma += funcion3[i][2] * x[i];
				n += funcion3[i][2];
			}
			return suma / n;
		}
	}

	private double[] marcaDeClase() {
		double[] x = new double[funcion3.length];
		for (int i = 0; i < funcion3.length; i++) {
			x[i] = (funcion3[i][0] + funcion3[i][1]) / 2.0;
		}
		return x;
	}

	private double mediaGeo() {
		if (serie == 1) {
			if (dimension == 1) {
				int producto = 1;
				for (int i = 0; i < funcion11.length; i++) {
					producto *= funcion11[i];
				}
				return Math.pow(producto, 1.0 / funcion11.length);
			} else {
				int producto = 1;
				for (int i = 0; i < funcion12.length; i++) {
					producto *= funcion12[i][xOrY];
				}
				return Math.pow(producto, 1.0 / funcion12.length);
			}
		} else if (serie == 2) {
			double producto = 1;
			double n = 0.0;
			for (int i = 0; i < funcion2.length; i++) {
				producto *= Math.pow(funcion2[i][0], funcion2[i][1]);
				n += funcion2[i][1];
			}
			return Math.pow(producto, 1.0 / n);
		} else {
			double producto = 1;
			double n = 0.0;
			double[] x = marcaDeClase();
			for (int i = 0; i < funcion3.length; i++) {
				producto *= Math.pow(x[i], funcion3[i][2]);
				n += funcion3[i][2];
			}

			return Math.pow(producto, 1.0 / n);
		}
	}

	private double mediaArm() {
		if (serie == 1) {
			if (dimension == 1) {
				double suma = 0.0;
				for (int i = 0; i < funcion11.length; i++) {
					suma += 1.0 / funcion11[i];
				}
				return funcion11.length / suma;
			} else {
				double suma = 0.0;
				for (int i = 0; i < funcion12.length; i++) {
					suma += 1.0 / funcion12[i][xOrY];
				}
				return funcion12.length / suma;
			}
		} else if (serie == 2) {
			double suma = 0.0;
			double n = 0.0;
			for (int i = 0; i < funcion2.length; i++) {
				suma += funcion2[i][1] / funcion2[i][0];
				n += funcion2[i][1];
			}
			return n / suma;
		} else {
			double suma = 0.0;
			double n = 0.0;
			double[] x = marcaDeClase();
			for (int i = 0; i < funcion3.length; i++) {
				suma += funcion3[i][2] / x[i];
				n += funcion3[i][2];
			}
			return n / suma;
		}
	}

	private double mediana() {
		if (serie == 1) {
			if (dimension == 1) {
				if (funcion11.length % 2 == 0) {
					return (funcion11[(funcion11.length / 2) - 1] + funcion11[(funcion11.length / 2)]) / 2;
				} else {
					return (funcion11[(int) (funcion11.length / 2)]);
				}
			} else {
				if (funcion12.length % 2 == 0) {
					return (funcion12[(funcion12.length / 2) - 1][xOrY] + funcion12[(funcion12.length / 2)][xOrY]) / 2;
				} else {
					return (funcion12[(int) (funcion12.length / 2)][xOrY]);
				}

			}
		} else if (serie == 2) {
			int n = 0;
			for (int i = 0; i < funcion2.length; i++) {
				n += funcion2[i][1];
			}

			double mediana = 0;
			double[] N = faa();
			for (int i = 0; i < funcion2.length; i++) {
				if (N[i] >= (n / 2)) {
					mediana = funcion2[i][0];
					break;
				}
			}
			return mediana;
		} else {
			int n = 0;
			double mediana = 0;
			for (int i = 0; i < funcion3.length; i++) {
				n += funcion3[i][2];
			}
			double[] N = faa();
			int indice = 0;
			for (int i = 0; i < funcion3.length; i++) {
				if (N[i] >= n / 2) {
					indice = i;
					break;
				}
			}
			double anchura = (funcion3[indice][1] - funcion3[indice][0]);
			if (indice > 0) {
				mediana = funcion3[indice][0]
						+ ((((n / 2.0) - N[indice - 1]) / funcion3[indice][2]) * anchura);
			} else {
				mediana = funcion3[indice][0]
						+ ((((n / 2.0)) / funcion3[indice][2]) * anchura);
			}
			return mediana;
		}
	}

	private double[] faa() {
		if (serie == 3) {
			double[] N = new double[funcion3.length];
			for (int i = 0; i < funcion3.length; i++) {
				if (i == 0) {
					N[i] = funcion3[i][2];
				} else {
					N[i] = funcion3[i][2] + N[i - 1];
				}
			}
			return N;
		} else if (serie == 2) {
			double[] N = new double[funcion2.length];
			for (int i = 0; i < funcion2.length; i++) {
				if (i == 0) {
					N[i] = funcion2[i][1];
				} else {
					N[i] = funcion2[i][1] + N[i - 1];
				}
			}
			return N;
		} else {
			return null;
		}
	}

	private double moda() {
		if (serie == 1) {
			return 0;
		} else if (serie == 2) {
			double moda = -1;
			for (int i = 0; i < funcion2.length; i++) {
				if (moda < funcion2[i][1]) {
					moda = funcion2[i][1];
				}
			}
			return moda;

		} else {
			double moda = 0;
			double[] h = alturas();
			double hMax = -1;
			int indice = 0;
			for (int i = 0; i < funcion3.length; i++) {
				if (hMax < h[i]) {
					hMax = h[i];
					indice = i;
				}
			}
			double anchura = (funcion3[indice][1] - funcion3[indice][0]);
			if (indice == 0) {
				moda = funcion3[indice][0]
						+ ((h[indice + 1] / (h[indice + 1])) * anchura);
			} else if (indice == funcion3.length - 1) {
				moda = funcion3[indice][0];
			} else {
				moda = funcion3[indice][0]
						+ ((h[indice + 1] / (h[indice + 1] + h[indice - 1])) * anchura);
			}
			return moda;
		}
	}

	private double[] alturas() {
		double[] h = new double[funcion3.length];
		double anchura = 0.0;
		for (int i = 0; i < funcion3.length; i++) {
			anchura = (funcion3[i][1] - funcion3[i][0]);
			h[i] = funcion3[i][2] / anchura;
		}
		return h;
	}

	public double[] ajusteCurvas() {
		if (qdc == 1) {
			if (!inverse) {
				xOrY = 1;
				double var = varianza();
				ajusteCurvas[0] = covarianza() / var;
				xOrY = 1;
				double px = mediaAri();
				xOrY = 0;
				ajusteCurvas[1] = mediaAri() - px * ajusteCurvas[0];
			} else {
				xOrY = 0;
				double var = varianza();
				ajusteCurvas[0] = covarianza() / var;
				xOrY = 1;
				double px = mediaAri();
				xOrY = 0;
				ajusteCurvas[1] = px - mediaAri() * ajusteCurvas[0];
				inverse = false;
			}
		} else if (qdc == 2) {
			if (!inverse) {
				xOrY = 1;
				double var = varianza();
				double a = covarianza() / var;
				xOrY = 2;
				double pY = mediaAri();
				xOrY = 1;
				double b = pY - mediaAri() * a;
				xOrY = 0;
				ajusteCurvas[0] = Math.pow(Math.E, b);
				ajusteCurvas[1] = Math.pow(Math.E, a);
			} else {
				xOrY = 0;
				double var = varianza();
				double a = covarianza() / var;
				xOrY = 3;
				double pX = mediaAri();
				xOrY = 0;
				double b = pX - mediaAri() * a;
				ajusteCurvas[0] = Math.pow(Math.E, b);
				ajusteCurvas[1] = Math.pow(Math.E, a);
				inverse = false;
			}
		} else {
			if (!inverse) {
				xOrY = 3;
				double var = varianza();
				double a = covarianza() / var;
				xOrY = 2;
				double pY = mediaAri();
				xOrY = 3;
				double b = pY - mediaAri() * a;
				xOrY = 0;
				ajusteCurvas[0] = Math.pow(Math.E, b);
				ajusteCurvas[1] = a;
			} else {
				xOrY = 2;
				double var = varianza();
				double a = covarianza() / var;
				xOrY = 3;
				double pY = mediaAri();
				xOrY = 2;
				double b = pY - mediaAri() * a;
				xOrY = 0;
				ajusteCurvas[0] = Math.pow(Math.E, b);
				ajusteCurvas[1] = a;
				inverse = false;
			}
		}
		return ajusteCurvas;
	}

	private double covarianza() {
		if (qdc == 1) {
			double suma = 0.0;
			for (int i = 0; i < funcion12.length; i++) {
				suma += (double) funcion12[i][0] * funcion12[i][1];
			}
			double promedioxy = suma / (double) funcion12.length;
			xOrY = 0;
			double medX = mediaAri();
			xOrY = 1;
			double medY = mediaAri();
			xOrY = 0;
			return promedioxy - medX * medY;
		} else if (qdc == 2) {
			if (!inverse) {
				double suma = 0.0;
				for (int i = 0; i < funcion12.length; i++) {
					suma += (double) funcion12[i][2] * funcion12[i][1];
				}
				double promedioxy = suma / (double) funcion12.length;
				xOrY = 2;
				double medX = mediaAri();
				xOrY = 1;
				double medY = mediaAri();
				xOrY = 0;
				return promedioxy - medX * medY;
			} else {
				double suma = 0.0;
				for (int i = 0; i < funcion12.length; i++) {
					suma += (double) funcion12[i][3] * funcion12[i][0];
				}
				double promedioxy = suma / (double) funcion12.length;
				xOrY = 3;
				double medX = mediaAri();
				xOrY = 0;
				double medY = mediaAri();
				xOrY = 0;
				return promedioxy - medX * medY;
			}
		} else {
			double suma = 0.0;
			for (int i = 0; i < funcion12.length; i++) {
				suma += (double) funcion12[i][2] * funcion12[i][3];
			}
			double promedioxy = suma / (double) funcion12.length;
			xOrY = 2;
			double medX = mediaAri();
			xOrY = 3;
			double medY = mediaAri();
			xOrY = 0;
			return promedioxy - medX * medY;
		}
	}

	public double coefRelac() {
		if (qdc == 1) {
			xOrY = 0;
			double desvX = desviacionEstandar();
			xOrY = 1;
			double desvY = desviacionEstandar();
			xOrY = 0;
			return covarianza() / (desvX * desvY);
		} else if (qdc == 2) {
			if (!inverse) {
				xOrY = 2;
				double desvX = desviacionEstandar();
				xOrY = 1;
				double desvY = desviacionEstandar();
				xOrY = 0;
				return covarianza() / (desvX * desvY);
			} else {
				xOrY = 3;
				double desvX = desviacionEstandar();
				xOrY = 0;
				double desvY = desviacionEstandar();
				xOrY = 0;
				return covarianza() / (desvX * desvY);
			}
		} else {
			xOrY = 2;
			double desvX = desviacionEstandar();
			xOrY = 3;
			double desvY = desviacionEstandar();
			xOrY = 0;
			return covarianza() / (desvX * desvY);
		}
	}

	public void restart() {
		funcion11 = null;
		funcion12 = null;
		funcion2 = null;
		funcion3 = null;
		qdc = 0;
		serie = 0;
		dimension = 0;
		xOrY = 0;
		inverse = false;
	}
}

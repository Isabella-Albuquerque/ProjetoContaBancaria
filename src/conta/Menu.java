package conta;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import conta.controller.ContaController;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.util.Cores;

public class Menu {

	public static void main(String[] args) {
		
		ContaController contas = new ContaController();
		Scanner leia = new Scanner(System.in);

		int opcao, numero, agencia, tipo, aniversario, numeroDestino;
        String titular;
        float saldo, limite, valor;

		while (true) {

			System.out.println(Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND
					+ "*****************************************************");
			System.out.println("                                                     ");
			System.out.println("                BANCO DO BRAZIL COM Z                ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("                                                     ");
			System.out.println("            1 - Criar Conta                          ");
			System.out.println("            2 - Listar todas as Contas               ");
			System.out.println("            3 - Buscar Conta por Numero              ");
			System.out.println("            4 - Atualizar Dados da Conta             ");
			System.out.println("            5 - Apagar Conta                         ");
			System.out.println("            6 - Sacar                                ");
			System.out.println("            7 - Depositar                            ");
			System.out.println("            8 - Transferir valores entre Contas      ");
			System.out.println("            9 - Sair                                 ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("Entre com a opção desejada:                          ");
			System.out.println("                                                     " + Cores.TEXT_RESET);
			
			try {
				opcao = leia.nextInt();
			}catch(InputMismatchException e){
				System.out.println("\nDigite valores inteiros!");
				leia.nextLine();
				opcao=0;
			}

			if (opcao == 9) {
				System.out.println("\nBanco do Brazil com Z - O seu futuro começa aqui!");
				sobre();
                 		leia.close();
				System.exit(0);
			}

			switch (opcao) {
		    case 1:
		        System.out.println(Cores.TEXT_WHITE + "Criar Conta\n\n");
		        System.out.println("Digite o Numero da Agência: ");
		        agencia = leia.nextInt();
		        leia.nextLine(); // Adicione esta linha para consumir o restante da linha após o número da agência
		        System.out.println("Digite o Nome do Titular: ");
		        titular = leia.nextLine();
		        do {
		            System.out.println("Digite o Tipo da Conta (1-CC ou 2-CP): ");
		            tipo = leia.nextInt();
		        } while(tipo < 1 && tipo > 2);
		        System.out.println("Digite o Saldo da Conta (R$): ");
		        saldo = leia.nextFloat();

		        switch(tipo) {
		            case 1 -> {
		                System.out.println("Digite o Limite de Crédito (R$): ");
		                limite = leia.nextFloat();
		                contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
		            }
		            case 2 -> {
		                System.out.println("Digite o dia do Aniversario da Conta: ");
		                aniversario = leia.nextInt();
		                contas.cadastrar(new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
		            }
		        }
		        keyPress();
		        break;
		
			
			case 2:
				
				System.out.println("\n Listar todas as Contas");
				contas.listarTodas();
				
				keyPress();
				break;
			
			case 3:
				System.out.println("\n Buscar Conta por número");
				System.out.println("Digite o número da conta: ");
				numero = leia.nextInt();
				
				contas.procurarPorNumero(numero);
			
				keyPress();
				break;
			
			case 4:
				  System.out.println("Atualizar dados da Conta\n\n");
				    System.out.println("Digite o número da conta: ");
				    numero = leia.nextInt();
				    leia.nextLine(); // Adicionado para consumir o restante da linha depois de ler o número da conta

				    var buscaConta = contas.buscarNaCollection(numero);

				    if (buscaConta != null) {
				        tipo = buscaConta.getTipo();
				        System.out.println("Digite o Numero da Agência: ");
				        agencia = leia.nextInt();
				        leia.nextLine(); // Adicionado para consumir o restante da linha depois de ler o número da agência
				        System.out.println("Digite o Nome do Titular: ");
				        titular = leia.nextLine();

				        System.out.println("Digite o Saldo da Conta (R$): ");
				        saldo = leia.nextFloat();
				        leia.nextLine(); // Adicionado para consumir o restante da linha depois de ler o saldo

				        switch (tipo) {
				            case 1 -> {
				                System.out.println("Digite o Limite de Crédito (R$): ");
				                limite = leia.nextFloat();
				                contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
				            }
				            case 2 -> {
				                System.out.println("Digite o dia do Aniversario da Conta: ");
				                aniversario = leia.nextInt();
				                contas.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));
				            }
				            default -> {
				                System.out.println("Tipo de conta inválido!");
				            }
				        }
				    } else {
				        System.out.println("A Conta não foi encontrada!");
				    }
				    keyPress();
				    break;
			
			case 5:
				System.out.println(Cores.TEXT_WHITE + "Apagar a Conta\n\n");
			    System.out.println("Digite o número da conta: ");
			    numero = leia.nextInt();

			    contas.deletar(numero);

			    keyPress();
			    break;
			
			case 6:
			    System.out.println(Cores.TEXT_WHITE + "Saque\n\n");
			    System.out.println("Digite o Numero da conta: ");
			    numero = leia.nextInt();

			    do {
			        System.out.println("Digite o Valor do Saque (R$): ");
			        valor = leia.nextFloat();
			    } while(valor <= 0);

			    contas.sacar(numero, valor);
			    keyPress();
			    break;
			
			case 7:
			    System.out.println(Cores.TEXT_WHITE + "Depósito\n\n");
			    System.out.println("Digite o Numero da conta: ");
			    numero = leia.nextInt();

			    do {
			        System.out.println("Digite o Valor do Depósito (R$): ");
			        valor = leia.nextFloat();
			    } while(valor <= 0);

			    contas.depositar(numero, valor);
			    keyPress();
			    break;
			
			case 8:
			    System.out.println(Cores.TEXT_WHITE + "Transferência entre Contas\n\n");
			    System.out.println("Digite o Numero da Conta de Origem: ");
			    numero = leia.nextInt();
			    System.out.println("Digite o Numero da Conta de Destino: ");
			    numeroDestino = leia.nextInt();

			    do {
			        System.out.println("Digite o Valor da Transferência (R$): ");
			        valor = leia.nextFloat();
			    } while(valor <= 0);

			    contas.transferir(numero, numeroDestino, valor);
			    keyPress();
			    break;

			default:
				
				
				System.out.println("\nOpção Inválida" + Cores.TEXT_RESET);
				
				keyPress();
				break;
			}
		}
	}

    	public static void sobre() {
		System.out.println("\n*********************************************************");
		System.out.println("Projeto Desenvolvido por: ");
		System.out.println("Generation Brasil - generation@generation.org");
		System.out.println("github.com/conteudoGeneration");
		System.out.println("*********************************************************");
	}
    
	public static void keyPress() {

		try {

			System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
			System.in.read();

		} catch (IOException e) {

			System.out.println("Você pressionou uma tecla diferente de enter!");

		}
	}
}
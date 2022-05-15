package com.juliocezar.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.juliocezar.cursomc.domain.Categoria;
import com.juliocezar.cursomc.domain.Cidade;
import com.juliocezar.cursomc.domain.Cliente;
import com.juliocezar.cursomc.domain.Endereco;
import com.juliocezar.cursomc.domain.Estado;
import com.juliocezar.cursomc.domain.Pagamento;
import com.juliocezar.cursomc.domain.PagamentoComBoleto;
import com.juliocezar.cursomc.domain.PagamentoComCartao;
import com.juliocezar.cursomc.domain.Pedido;
import com.juliocezar.cursomc.domain.Produto;
import com.juliocezar.cursomc.domain.enums.EstadoPagamento;
import com.juliocezar.cursomc.domain.enums.TipoCliente;
import com.juliocezar.cursomc.repositories.CategoriaRepository;
import com.juliocezar.cursomc.repositories.CidadeRepository;
import com.juliocezar.cursomc.repositories.ClienteRepository;
import com.juliocezar.cursomc.repositories.EnderecoRepository;
import com.juliocezar.cursomc.repositories.EstadoRepository;
import com.juliocezar.cursomc.repositories.PagamentoRepository;
import com.juliocezar.cursomc.repositories.PedidoRepository;
import com.juliocezar.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		
		Produto p1 = new Produto(null,"Computador", 2000.00);
		Produto p2 = new Produto(null,"Impressora", 800.00);
		Produto p3 = new Produto(null,"Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
				
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		
		Estado est1 = new Estado(null, "Pernambuco");
		Estado est2 = new Estado(null, "Alagoas");
		
		Cidade c1 = new Cidade(null, "Recife",est1);
		Cidade c2 = new Cidade(null, "Maceió",est2);
		Cidade c3 = new Cidade(null, "Pilar",est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null,"Julio Cezar","julio.cezar@teste.com","114.872.172.02", TipoCliente.PESSOAFISICA);

		cli1.getTelefones().addAll(Arrays.asList("81992000000","81999000009"));
		
		Endereco e1 = new Endereco(null,"Rua Teste", "110", "Apto 703", "Jardim", "50123808", cli1, c1);
		Endereco e2 = new Endereco(null,"Av Latão", "69", "Casa", "Centro", "55555090", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("23/10/2022 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("25/10/2022 08:32"), cli1, e2);
		
		Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pag1);
		
		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("12/11/2022 00:00"),  null);
		ped2.setPagamento(pag2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));
	}

}

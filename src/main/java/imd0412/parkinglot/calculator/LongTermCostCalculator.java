package imd0412.parkinglot.calculator;

public class LongTermCostCalculator implements TermCostCalculator {

	private static final Integer DIARIA_DE_TRINTA = 30;
	private static final Integer DIA = 24;
	private static final Integer MES = DIA*30;
	private static final Integer SEMANA = 168;
	private static final Integer DIARIA_DE_CINQUENTA = 50;

	@Override
	public Float calcular(long horasEstacionado) {
		Float cost = 70F;
		
		cost += adicionarTaxaDiaria50Reais(horasEstacionado);
		cost += adicionarTaxaDiaria30Reais(horasEstacionado);
		cost += adicionarTaxaMensal500Reais(horasEstacionado);
		
		
		return cost;
	}

	private Float adicionarTaxaMensal500Reais(long horasEstacionado) {
		Float extra = 0F;
		if(horasEstacionado > MES) {
			extra += horasEstacionado/MES*500;
		}
		return extra;
	}

	private Float adicionarTaxaDiaria30Reais(long horasEstacionado) {
		Float extra = 0F;
		
		if (horasEstacionado > SEMANA) {
			int qtoDiasExtraDe50Reais = (SEMANA-DIA)/DIA; // 6 dias
			extra += (qtoDiasExtraDe50Reais*DIARIA_DE_CINQUENTA);		// Adicona a taxa de 50 por 6 dias de estacionamento
			
			horasEstacionado -= SEMANA;		// Desconta os 6 dias do tempo estacionado
			
			if (horasEstacionado >= DIA) {
				long qtoDiasExtraDe30Reais = (horasEstacionado)/DIA;
				extra += (qtoDiasExtraDe30Reais*DIARIA_DE_TRINTA);
			}
		}
		return extra;
	}

	private Float adicionarTaxaDiaria50Reais(long horasEstacionado) {
		Float extra = 0F;
		
		if (horasEstacionado > DIA && horasEstacionado <= SEMANA	) { 	// Adiciona a diaria de 50 rais a cada 24 horas (desconciderando as primeiras 24 horas)
			long qtoDiasExtra = (horasEstacionado-DIA)/DIA;
			extra += (qtoDiasExtra*DIARIA_DE_CINQUENTA);
		}
		return extra ;
	}

}

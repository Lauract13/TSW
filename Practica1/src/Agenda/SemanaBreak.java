package Agenda;



public class SemanaBreak {

	    // Representa una semana de lunes a viernes.
	
	    public static final int DIAS_RESERVABLES = 5;
	    
	    // Numero de la semana en el año (0-52).
	    
	    private final int numeroSemana;
	    private final DiaBreak[] dias;

	    /**
	      * @param numeroSemana (0-52).
	     */
	    public SemanaBreak(int numeroSemana) throws DatoException
	    {
	    	if (numeroSemana <= 0 || numeroSemana > 52) {
				
				throw new DatoException("La semana debe tomar valor entre 0 y 52");
			}
	        this.numeroSemana = numeroSemana;
	        dias = new DiaBreak[DIAS_RESERVABLES];
	        int diaDelAnio = (numeroSemana) * 7 + 1;
	        for(int dia = 0; dia < DIAS_RESERVABLES; dia++) {
	            dias[dia] = new DiaBreak(diaDelAnio);
	            diaDelAnio++;
	        }
	    }

	    /**
	     * @param hora de la cita
	     * @param dia de la cita(1-5)
	     * @return Cita de un dia a una hora .
	     */
	    public String mostrarCita(int hora, int diaSemana)
	    {   	    	
	        return getDia(diaSemana).muestraCita(hora);
	    }

	    /**
	     * @param dia de la semana (1..DIAS_RESERVABLES).
	     * @return El Dia asociado, o null si dia de la semana
	     *         esta fuera de rango.
	     */
	    public DiaBreak getDia(int diaSemana)
	    {
	        if(diaSemana >= 1 && diaSemana <= DIAS_RESERVABLES) {
	            return dias[diaSemana];
	        }
	        else {
	            return null;
	        }
	    }

	    /**
	     * @return Numero de semana (0-52).
	     */
	    public int getNumeroSemana()
	    {
	        return numeroSemana;
	    }
	    
	   	    
	    public String primerHueco(int duracion) {
	    	
	    	String disponible;
	    	for(int dia = 1; dia < DIAS_RESERVABLES; dia++) {
	    		int hueco=dias[dia].buscaSlot(duracion);
	    		if (hueco!=-1)
	             { disponible= diaSemana(dia) + " " + hueco + ":00";
	    			return disponible;
	             }
	    	}
	             
	    	return "No hay disponibilidad";
	    }
	    
	    /**
	     * @return Nombre Dia de la semana
	     */
	    public String diaSemana(int dia) {
	    
	    	String diaNombre;
	    	
	    	switch(dia) {
	    	 case 0: 
	    		 diaNombre="Lunes";
	    	     break;
	    	 case 1: 
	    		 diaNombre="Martes";
	    	     break;
	    	 case 2: 
	    		 diaNombre="Miercoles";
	    	     break;
	    	 case 3: 
	    		 diaNombre="Jueves";
	    	     break;
	    	 case 4: 
	    		 diaNombre="Viernes";
	    	 default: 
	    		 diaNombre="No citable";
	    	     break;
	    	 }
		    
		    return diaNombre;
	    }
	    
	    
	}

$(document).ready(function() {
	$('#examenModal').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget);
		var registro = button.data('registro')
		var comunicacion = button.data('comunicacion');
		var razonamiento = button.data('razonamiento');
		var lectura = button.data('lectura');
		var competencias = button.data('cciudadanas');
		var ingles = button.data('ingles');
		var fProyectos = button.data('fproyectos');
		var pCientifico = button.data('pcientifico');
		var dSoftware = button.data('dsoftware');
		var nIngles = button.data('ningles');
		var total = button.data('total');
		var anulado = button.data('anulado');

		function getNivelClass(score) {
			if (score <= 300 && score >= 191) {
				return "nivel-4";
			} else if (score <= 190 && score >= 156) {
				return "nivel-3";
			} else if (score <= 155 && score >= 126) {
				return "nivel-2";
			} else if (score <= 125 && score >= 0) {
				return "nivel-1";
			} else {
				return "";
			}
		}

		$('#modalContent').html(`
			<p>
												<strong>Número de registro:</strong> <span
													id="modalRegistro"></span>
											</p>
											<p>
												<strong>Comunicación escrita:</strong> <span
													id="modalComunicacion"></span> <span
													id="modalComunicacionColor" class="puntaje-color"></span>
											</p>
											<p>
												<strong>Razonamiento cuantitativo:</strong> <span
													id="modalRazonamiento"></span> <span
													id="modalRazonamientoColor" class="puntaje-color"></span>
											</p>
											<p>
												<strong>Lectura crítica:</strong> <span id="modalLectura"></span>
												<span id="modalLecturaColor" class="puntaje-color"></span>
											</p>
											<p>
												<strong>Competencias ciudadanas:</strong> <span id="modalCompetencias"></span>
												<span id="modalCompetenciasColor" class="puntaje-color"></span>
											</p>
											<p>
												<strong>Inglés:</strong> <span id="modalIngles"></span>
												<span id="modalInglesColor" class="puntaje-color"></span>
											</p>
											<p>
												<strong>Formulación de proyectos de ingeniería:</strong> <span
													id="modalFproyectos"></span> <span
													id="modalFproyectosColor" class="puntaje-color"></span>
											</p>
											<p>
												<strong>Pensamiento Cientifíco-Matemáticas y
													estadística:</strong> <span id="modalPcientifico"></span>
													<span id="modalPcientificoColor" class="puntaje-color"></span>
											</p>
											<p>
												<strong>Diseño de software:</strong> <span id="modalDsoftware"></span>
												<span id="modalDsoftwareColor" class="puntaje-color"></span>
											</p>
											<p>
												<strong>Nivel de inglés:</strong> <span id="modalNingles"></span>
											</p>
											<p>
												<strong>Total:</strong> <span id="modalTotal"></span>
												<span id="modalTotalColor" class="puntaje-color"></span>
											</p>
					`);

		if (anulado) {
			$('#modalContent').html('<p class="text-danger"><strong>Este examen ha sido anulado.</strong></p>');
		} else {

			$('#modalRegistro').text(registro);

			$('#modalComunicacion').text(comunicacion);
			$('#modalComunicacionColor').text(getNivelClass(comunicacion) ? " " + getNivelClass(comunicacion) : "").attr('class', getNivelClass(comunicacion));

			$('#modalRazonamiento').text(razonamiento);
			$('#modalRazonamientoColor').text(getNivelClass(razonamiento) ? " " + getNivelClass(razonamiento) : "").attr('class', getNivelClass(razonamiento));

			$('#modalLectura').text(lectura);
			$('#modalLecturaColor').text(getNivelClass(lectura) ? " " + getNivelClass(lectura) : "").attr('class', getNivelClass(lectura));

			$('#modalCompetencias').text(competencias);
			$('#modalCompetenciasColor').text(getNivelClass(competencias) ? " " + getNivelClass(competencias) : "").attr('class', getNivelClass(competencias));

			$('#modalIngles').text(ingles);
			$('#modalInglesColor').text(getNivelClass(ingles) ? " " + getNivelClass(ingles) : "").attr('class', getNivelClass(ingles));

			$('#modalFproyectos').text(fProyectos);
			$('#modalFproyectosColor').text(getNivelClass(fProyectos) ? " " + getNivelClass(fProyectos) : "").attr('class', getNivelClass(fProyectos));

			$('#modalPcientifico').text(pCientifico);
			$('#modalPcientificoColor').text(getNivelClass(pCientifico) ? " " + getNivelClass(pCientifico) : "").attr('class', getNivelClass(pCientifico));

			$('#modalDsoftware').text(dSoftware);
			$('#modalDsoftwareColor').text(getNivelClass(dSoftware) ? " " + getNivelClass(dSoftware) : "").attr('class', getNivelClass(dSoftware));

			$('#modalNingles').text(nIngles);

			$('#modalTotal').text(total);
			$('#modalTotalColor').text(getNivelClass(total) ? " " + getNivelClass(total) : "").attr('class', getNivelClass(total));
		}

	});
});
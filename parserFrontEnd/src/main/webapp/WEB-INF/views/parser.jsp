<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">

	<div class="row">

		<div class="col-lg-3">

			<%@include file="./shared/sidebar.jsp"%>

		</div>
		<!-- /.col-lg-3 -->

		<div class="col-lg-9">
			<div class="row">
				<form method="post" id="nt_impact_drill-form"
					action="${contextRoot}/parser">
					<table class="table">
						<thead>
							<tr>
								<th>#</th>
								<th>Method Name</th>

							</tr>
						</thead>
						<tbody>
							<tr>
								<td><div class="form-check">
										<label class="form-check-label"> <input
											class="form-check-input" type="checkbox" name="cb_nt_impact_drill" value="nt_impact_drill">
										</label>
									</div></td>
								<td>nt_impact_drill</td>
							</tr>
							<tr>
								<td><div class="form-check">
										<label class="form-check-label"> <input
											class="form-check-input" type="checkbox" name="cb_nt_impact_drill222" value="nt_impact_drill222">
										</label>
									</div></td>
								<td>nt_impact_drill222</td>
							</tr>

						</tbody>
					</table>
					<input class="btn btn-primary btn-lg btn-block" type="submit"
						value="Start parser">
				</form>
			</div>

			<!-- /.row -->

		</div>
		<!-- /.col-lg-9 -->

	</div>
	<!-- /.row -->

</div>
<!-- /.container -->
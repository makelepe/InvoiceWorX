<div class="main-content">
	<div class="row padding-top">
		<div class="col-md-10 col-md-offset-1">
			 <div class="row">
			<form>

				<div class="panel panel-default">
					<p class="panel-heading no-collapse">Reset password</p>
					<div class="panel-body">
					 <p align="center" >${sessionScope.success_msg}</p>
						<div class="form-group">
							<label>Old password</label> <input type="password"
								name=oldPassword value="" class="form-control span12">
						</div>
						<div class="form-group">
							<label>New password</label> <input type="password"
								name="newPassword" value="" class="form-control span12">
						</div>
						<div class="form-group">
							<label>Username</label> <input type="text" name="username"
								value="" class="form-control span12">
						</div>


						<div class="form-group">

							<input type="hidden" name="action" value="reset" />
							<p>
								<input type="submit" value="Reset password"
									class="btn btn-primary pull-right" />
							</p>
						</div>
						<div class="clearfix"></div>

					</div>
				</div>

			</form>
			</div>
		</div>
	</div>
</div>


<template>


<!-- Preloader -->
<div class="landing-page">
<div id="mypreloader">
<loading></loading>
</div>
<div class="content-bg-wrap"></div>




<div class="header-spacer--standard"></div>

<div class="container">
	<div class="row display-flex">
		<div class="col col-xl-6 col-lg-6 col-md-12 col-sm-12 col-12">
			<div class="landing-content">
				<h1>Welcome to the LolSearcher</h1>
				<p>Hope you can have fun here
				</p>
				<a href="Landingpage" class="btn btn-md btn-border c-white">Register Now!</a>
			</div>
		</div>

		<div class="col col-xl-5 col-lg-6 col-md-12 col-sm-12 col-12">
			
			<!-- Login-Registration Form  -->
			
			<div class="registration-login-form">
				<!-- Nav tabs -->
				<ul class="nav nav-tabs" role="tablist">
					<li class="nav-item">
						<a class="nav-link active" data-toggle="tab" href="#home" role="tab">
							<svg class="olymp-login-icon"><use xlink:href="svg-icons/sprites/icons.svg#olymp-login-icon"></use></svg>
						</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" data-toggle="tab" href="#profile" role="tab">
							<svg class="olymp-register-icon"><use xlink:href="svg-icons/sprites/icons.svg#olymp-register-icon"></use></svg>
						</a>
					</li>
				</ul>
			
				<!-- Tab panes -->
				<div class="tab-content">
					<div class="tab-pane active" id="home" role="tabpanel" data-mh="log-tab">
						<div class="title h6">Login to your Account</div>
						<form class="content" method="post" onsubmit="return false;" data-abide novalidate>
							<div class="row">
								<div class="col col-12 col-xl-12 col-lg-12 col-md-12 col-sm-12">
									<div class="form-group label-floating">
										<label class="control-label">Your Email</label>
										<input class="form-control" placeholder="" type="email" v-model="user.account" required>
									</div>
									<div class="form-group label-floating">
										<label class="control-label">Your Password</label>
										<input class="form-control"  placeholder="" type="password" v-model="user.password" required>
									</div>
			
									<div class="remember">
			
										<div class="checkbox">
											<label>
												<input name="optionsCheckboxes" type="checkbox">
												Remember Me
											</label>
										</div>
									</div>									
    								<a href="#" class="btn btn-lg btn-primary full-width" @click="login">Login</a>

									
			
			
									<p>Don’t you have an account? <a href="Landingpage">Register Now!</a> it’s really simple and you can start enjoing all the benefits!</p>
								</div>
							</div>
						</form>
					</div>

					<div class="tab-pane" id="profile" role="tabpanel" data-mh="log-tab">
						<div class="title h6">Register to Olympus</div>
						<form class="content" id="register" onsubmit="return false;" data-abide novalidate>
							<div class="row">
								<div class="col col-12 col-xl-12 col-lg-12 col-md-12 col-sm-12">
									<div class="form-group label-floating">
										<label class="control-label">Your NickName</label>
										<input class="form-control" placeholder="" type="text"  v-model="registerInfo.nickName" required>
									</div>
								</div>
								<div class="col col-12 col-xl-12 col-lg-12 col-md-12 col-sm-12">
									<div class="form-group label-floating">
										<label class="control-label">Your Email</label>
										<input class="form-control" placeholder="" type="email"  v-model="registerInfo.account" required>
									</div>
									<div class="form-group label-floating">
										<label class="control-label">Your Password</label>
										<input class="form-control" placeholder="" type="password"  id="password" v-model="registerInfo.password" required>
									</div>
    								<button class="btn btn-purple btn-lg full-width" @click="register">Complete Registration!</button>
									
								</div>
							</div>
						</form>
					</div>
		
				</div>
			</div>
			
			<!-- ... end Login-Registration Form  -->		</div>
	</div>
</div>


<!-- ... end Window-popup Restore Password -->


<!-- Window Popup Main Search -->

<div class="modal fade" id="main-popup-search" tabindex="-1" role="dialog" aria-labelledby="main-popup-search" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered window-popup main-popup-search" role="document">
		<div class="modal-content">
			<a href="#" class="close icon-close" data-dismiss="modal" aria-label="Close">
				<svg class="olymp-close-icon"><use xlink:href="svg-icons/sprites/icons.svg#olymp-close-icon"></use></svg>
			</a>
			<div class="modal-body">
				<form class="form-inline search-form" method="post">
					<div class="form-group label-floating">
						<label class="control-label">What are you looking for?</label>
						<input class="form-control bg-white" placeholder="" type="text" value="">
					</div>

					<button class="btn btn-purple btn-lg">Search</button>
				</form>
			</div>
		</div>
	</div>
</div>

<!-- ... end Window Popup Main Search -->

</div>
</template>>
<script>
import loading from '@/components/subgroup/Loading.vue'
setTimeout(function () {
			$('#mypreloader').fadeOut(600);
		}, 1500)
export default {
  name: 'resgister',
  data () {
    return {
	  registerInfo: {nickName: '', account: '', password: ''},
	   user: {account: '', password: ''}
    }
  },
  methods: {
    register () {
      if (this.registerInfo.password !== '') {
        this.$axios
          .post('/loginAndlogon-producer/api/register', {
            nickName: this.registerInfo.nickName,
            account: this.registerInfo.account,
            password: this.registerInfo.password
          })
          .then(successResponse => {
            this.responseResult = JSON.stringify(successResponse.data)
            if (successResponse.data.code === 200) {
              alert(successResponse.data.data)
            }
            if (successResponse.data.code === 400) {
              alert(successResponse.data.message)
            }
          })
          .catch(failResponse => {})
      }
	},
	login () {
		if(sessionStorage.getItem("IsLog")){
			alert("Your have login already")
		}else{
	 	this.$axios
        .post('/loginAndlogon-producer/api/login', {
          account: this.user.account,
          password: this.user.password
        })
        .then(successResponse => {
		  this.responseResult = JSON.stringify(successResponse.data)
          if (successResponse.data.code === 200) {
			sessionStorage.setItem("UuAccount",this.user.account)
			sessionStorage.setItem("UuID",successResponse.data.data)
			sessionStorage.setItem("IsLog",true)
			this.init()
			this.$router.push({name:'Homepage'});
          }
          if (successResponse.data.code === 400) {
            alert(successResponse.data.message)
          }
        })
        .catch(failResponse => {})
		}
    
	},
	init() {
		this.$axios
		.get('/manageInformation-producer/api/myInformation/' + sessionStorage.getItem("UuID"))
        .then(successResponse => {
			this.responseResult = JSON.stringify(successResponse.data)
        	if (successResponse.data.code === 200) {
				sessionStorage.setItem("UuName",successResponse.data.data.nickName)
				sessionStorage.setItem("UuImage",successResponse.data.data.icon)
				sessionStorage.setItem("UuGender",successResponse.data.data.gender)
				sessionStorage.setItem("UuPrefer",successResponse.data.data.prefer)
        	}
        	if (successResponse.data.code === 400) {
				alert(successResponse.data.message)
        	}
        })
        .catch(failResponse => {})
	},
	
  },
  components:{
	  loading
  }
}
</script>



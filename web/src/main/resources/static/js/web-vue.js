//var employeesUrl = "http://192.168.100.96:8765/employee/employees";
var employeesUrl = "http://192.168.100.96:3333/employees";
Vue.component('employee-card', {
	props: ['employee'],
	template: '#template',
	methods: {
		deleteEmployee: function(id){
			var self = this;
			var _url = employeesUrl + "/" + id;
			axios({
				method: 'delete',
				headers: { 'content-type': 'application/json;charset=UTF-8' },
				url: _url,
				data: self.employeeObj
			})
			.then(function (response) {
				self.$parent.getEmployee();
			})
			.catch(function (error) {
				console.log(error);
			});
		}
	}
});
var app = new Vue({
	el: '#employee-info',
	data: {
		employees: [],
		employeeObj : {
			id : null,
			name : '',
			age : null,
			addressObj : {
				number : null,
				name : null
			}
		}
	},
	created: function (){
		this.getEmployee();
	},
	methods: {
		addEmployee: function(){
			var self = this;
			axios({
				method: 'post',
				headers: { 'content-type': 'application/json;charset=UTF-8' },
				url: employeesUrl,
				data: self.employeeObj
			})
			.then(function (response) {
				self.employeeObj = {
					id : null,
					name : '',
					age : null,
					addressObj : {
						number : null,
						name : null
					}
				};
				self.getEmployee();
			})
			.catch(function (error) {
				console.log(error);
			});
		},
		getEmployee: function(){
			var self = this;
			axios.get(employeesUrl, {
			})
			.then(function (response) {
				self.employees = response.data;
			})
			.catch(function (error) {
				console.log(error);
			});
		}
	}
});

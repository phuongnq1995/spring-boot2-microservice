//var employeesUrl = "http://192.168.100.96:8765/employee/employees";
var employeesUrl = "http://192.168.100.96:3333/employees";

class App extends React.Component{
	constructor() {
		super();
		this.state = {
			employees: []
		};
		this.deleteEmployee = this.deleteEmployee.bind(this);
		this.getEmployee = this.getEmployee.bind(this);
	}

	componentDidMount() {
		this.getEmployee();
	}

	getEmployee() {
		console.log("getEmployee");
		var self = this;
		axios.get(employeesUrl, {
		})
		.then(function (response) {
			self.setState({employees: response.data});
		})
		.catch(function (error) {
			console.log(error);
		});
	}

	deleteEmployee(id) {
		console.log("delete employee app");
		var self = this;
		var _url = employeesUrl + "/" + id;
		axios({
			method: 'delete',
			headers: { 'content-type': 'application/json;charset=UTF-8' },
			url: _url
		})
		.then(function (response) {
			//that.employees.push(response.data);
			self.getEmployee();
		})
		.catch(function (error) {
			console.log(error);
		});
	}

	render() {
		var rows = this.state.employees.map((employee) => <Employee employee={employee} 
								key={employee.id} 
								deleteEmployee={this.deleteEmployee} /> );
		return (<div>
		<EmployeeForm getEmployee={this.getEmployee} />
		<h2>Employee list</h2>
		<table className="table table-striped">
			<thead>
				<tr>
					<th>Delete</th>
					<th>Name</th>
					<th>Age</th>
					<th>Address</th>
					<th>Port</th>
				</tr>
			</thead>
			<tbody>
				{rows}
			</tbody>
		</table></div>);
	}
}

class Employee extends React.Component{
	constructor() {
		super();
	}
	del(id) {
		//call to parent
		console.log("delete employee");
		this.props.deleteEmployee(id);
	}
	render() {
		return (<tr>
			<td>
				<button type="button" className="btn btn-danger" 
					onClick= { () => this.del(this.props.employee.id)} >-</button>
				<input type="hidden" value={this.props.employee.id} />
			</td>
			<td>{ this.props.employee.name }</td>
			<td>{ this.props.employee.age }</td>
			<td>{ this.props.employee.addressObj.number }-{ this.props.employee.addressObj.name }</td>
			<td>{ this.props.employee.addressObj.port }</td>
		</tr>);
	}
}
const initForm = {
	id : "",
	name : '',
	age : "",
	addressObj : {
		number : "",
		name : ""
	}
}
class EmployeeForm extends React.Component{
	constructor(props) {
		super(props);
		this.state = initForm;
		this.handleEmployeeChange = this.handleEmployeeChange.bind(this);
		this.handleAddressChange = this.handleAddressChange.bind(this);
		this.addEmployee = this.addEmployee.bind(this);
	}

	reset(){
		this.setState(initForm);
	}

	handleEmployeeChange (event) {
		console.log([event.target.name]+" : "+event.target.value);
		this.setState({[event.target.name] : event.target.value });//set state
	}

	handleAddressChange (event) {
		let addressObj = Object.assign({}, this.state.addressObj);//creating copy of object
		addressObj[event.target.name] = event.target.value;//updating value
		this.setState({addressObj});//set state
	}

	addEmployee (e){
		console.log("addEmployee");
		var self = this;
		var param = self.state;
		axios({
			method: 'post',
			headers: { 'content-type': 'application/json;charset=UTF-8' },
			url: employeesUrl,
			data: param
		})
		.then(function (response) {
			//that.employees.push(response.data);
			self.props.getEmployee();
			self.reset();
		})
		.catch(function (error) {
			console.log(error);
		});
	}

	render() {
		return (
				<form id="employee-form" enctype='application/json' >
				<h4>Information</h4>
				<p>{this.state.name}</p>
				<input type="hidden" name="id" onChange={this.handleEmployeeChange} value={this.state.id} />
				<div className="form-group row">
					<label for="name" className="col-sm-1 col-form-label">Name</label>
					<div className="col-sm-5">
						<input type="text" className="form-control-plaintext" name="name"
							id="name" placeholder="Name" onChange={this.handleEmployeeChange}
							value={this.state.name} />
					</div>
					<label for="age" className="col-sm-1 col-form-label">Age</label>
					<div className="col-sm-5">
						<input type="text" className="form-control-plaintext" id="age" name="age"
							placeholder="Age" onChange={this.handleEmployeeChange} 
							value={this.state.age} />
					</div>
				</div>
				<h5>Address</h5>
				<div className="form-group row">
					<label for="number" className="col-sm-1 col-form-label">Number</label>
					<div className="col-sm-5">
						<input type="text" className="form-control-plaintext" name="number"
							id="number" placeholder="Number" onChange={this.handleAddressChange} 
							value={this.state.addressObj.number} />
					</div>
					<label for="name" className="col-sm-1 col-form-label">Name</label>
					<div className="col-sm-5">
						<input type="text" className="form-control-plaintext" id="name" 
							placeholder="name" onChange={this.handleAddressChange} name="name"
							value={this.state.addressObj.name}/>
					</div>
				</div>
				<a type="button" className="btn btn-primary" onClick= { () =>this.addEmployee()}>Add</a>
			</form>
		);
	}
}
ReactDOM.render(<App />, document.getElementById('root') );
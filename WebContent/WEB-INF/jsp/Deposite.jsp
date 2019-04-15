<!DOCTYPE HTML>
<html>
<head>
    <title>Axis Online Banking Application</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<link rel="stylesheet" href="css/style.css">
</head>

<body>
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <!-- Brand -->
        <a class="navbar-brand" href="#">AXIS BANK</a>
      
        <!-- Links -->
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link" href="/BankAppTemplate/app/BankAccount/home/new">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/BankAppTemplate/app/BankAccount/home/new-acc">New Registration</a>
          </li>
      
          <!-- Dropdown -->
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
              Explore
            </a>
            <div class="dropdown-menu">
              <a class="dropdown-item" href="/BankAppTemplate/app/BankAccount/home/withdrawn">Withdrawl Amount </a>
              <a class="dropdown-item" href="/BankAppTemplate/app/BankAccount/home/Deposite">Deposite Amount</a>
              <a class="dropdown-item" href="Fund.html">Fund Transfer</a>
              <a class="dropdown-item" href="/BankAppTemplate/app/BankAccount/home/Checkbal">Check Balance</a>
              <a class="dropdown-item" href="Accdetails.html">Account Details</a>
              <a class="dropdown-item" href="Search.html">Search Account</a>
              <a class="dropdown-item" href="/BankAppTemplate/app/BankAccount/home/Delete">Delete Account</a>
              <a class="dropdown-item" href="Update.html">Update Account</a> 
            </div>
          </li>
        </ul>
      </nav>
    <form action="/BankAppTemplate/app/BankAccount/Deposite" class="form-box" method="post">
            <h2 class="new-heading">Deposit Amount</h2>
        <div class="row">
            <div class="form-group col-md-6">
                <label for="formGroupExampleInput">Account ID</label>
                <input type="text" name="account_id" class="form-control"  maxlength="15" id="formGroupExampleInput" placeholder="Account ID">
            </div>
            <div class="form-group col-md-6">
                <label for="formGroupExampleInput2">Amount</label>
                <input type="text" name="account_balance" class="form-control"  id="formGroupExampleInput2" placeholder="Amount">
            </div>
            <button type="submit" class="btn btn-primary sbtn">Deposite</button>
        </div>
        
    </form>
    
    
</body>
</html>
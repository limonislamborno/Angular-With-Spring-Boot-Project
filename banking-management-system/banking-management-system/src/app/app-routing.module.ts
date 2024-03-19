import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddCustomerComponent } from './components/add-customer/add-customer.component';
import { ViewCustomerComponent } from './components/view-customer/view-customer.component';
import { DepositComponent } from './components/deposit/deposit.component';
import { WithdrawComponent } from './components/withdraw/withdraw.component';
import { TransferComponent } from './components/transfer/transfer.component';
import { LoanAplicationFormComponent } from './components/loan-aplication-form/loan-aplication-form.component';
import { LoanAplicationListComponent } from './components/loan-aplication-list/loan-aplication-list.component';
import { LoanCheckComponent } from './components/loan-check/loan-check.component';
import { LoanAboutComponent } from './components/loan-about/loan-about.component';
import { LoanAboutViewComponent } from './components/loan-about-view/loan-about-view.component';
import { TransactionHistoryComponent } from './components/transaction-history/transaction-history.component';
import { TransactionHistory2Component } from './components/transaction-history2/transaction-history2.component';
import { LoanPaymentFormComponent } from './components/loan-payment-form/loan-payment-form.component';

import { Dashboard1Component } from './components/dashboard1/dashboard1.component';
import { Dashboard2Component } from './components/dashboard2/dashboard2.component';
import { SearchCustomerComponent } from './components/search-customer/search-customer.component';
import { DpsAboutViewComponent } from './components/dps-about-view/dps-about-view.component';
import { DpsAboutComponent } from './components/dps-about/dps-about.component';
import { LoanInformationComponent } from './components/loan-information/loan-information.component';
import { ReportComponent } from './components/report/report.component';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { RegistrationPageComponent } from './components/registration-page/registration-page.component';
import { ViewCustomer2Component } from './components/view-customer2/view-customer2.component';
import { TransactionHistoryAdminComponent } from './components/transaction-history-admin/transaction-history-admin.component';
import { DpsInformationComponent } from './components/dps-information/dps-information.component';
import { TransactionHistory2AdminComponent } from './components/transaction-history2-admin/transaction-history2-admin.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },

  { path: 'addcustomer', component: AddCustomerComponent },
  { path: 'viewcustomer', component: ViewCustomerComponent },
  { path: 'viewcustomer2', component: ViewCustomer2Component },
  { path: 'deposit', component: DepositComponent },
  { path: 'withdraw', component: WithdrawComponent },
  { path: 'transfer', component: TransferComponent },
  { path: 'loanapply', component: LoanAplicationFormComponent },
  { path: 'loanlist', component: LoanAplicationListComponent },
  { path: 'loancheck', component: LoanCheckComponent },
  { path: 'loanaboutview', component: LoanAboutComponent },
  { path: 'loanabout', component: LoanAboutViewComponent },
  { path: 'transactionhistory', component: TransactionHistoryComponent },
  { path: 'transactionhistoryadmin', component: TransactionHistoryAdminComponent },
  { path: 'transactionhistory2', component: TransactionHistory2Component },
  { path: 'transactionhistory2admin', component: TransactionHistory2AdminComponent },
 { path: 'loanpaymentform', component: LoanPaymentFormComponent },
 { path: 'search', component: SearchCustomerComponent },
 { path: 'dashboard1', component: Dashboard1Component },
 { path: 'dashboard2', component: Dashboard2Component },
 { path: 'dpsaboutview', component: DpsAboutViewComponent },
 { path: 'dpsabout', component: DpsAboutComponent },
 { path: 'loaninformation', component: LoanInformationComponent },
 { path: 'report', component: ReportComponent },
 { path: 'login', component: LoginPageComponent },
 { path: 'registration', component: RegistrationPageComponent },
 { path: 'dpsinformation', component: DpsInformationComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

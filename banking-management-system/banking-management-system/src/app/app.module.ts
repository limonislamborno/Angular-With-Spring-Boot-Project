import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddCustomerComponent } from './components/add-customer/add-customer.component';
import { ViewCustomerComponent } from './components/view-customer/view-customer.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { DepositComponent } from './components/deposit/deposit.component';
import { SafePipe } from './components/deposit/safe.pipe';
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
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { OkComponent } from './components/ok/ok.component';
import { Dashboard1Component } from './components/dashboard1/dashboard1.component';
import { Dashboard2Component } from './components/dashboard2/dashboard2.component';
import { Header2Component } from './components/header2/header2.component';
import { Sidebar2Component } from './components/sidebar2/sidebar2.component';

import { SearchCustomerComponent } from './components/search-customer/search-customer.component';
import { DpsAboutViewComponent } from './components/dps-about-view/dps-about-view.component';
import { DpsAboutComponent } from './components/dps-about/dps-about.component';
import { LoanInformationComponent } from './components/loan-information/loan-information.component';
import { ReportComponent } from './components/report/report.component';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { RegistrationPageComponent } from './components/registration-page/registration-page.component';
import { ViewCustomer2Component } from './components/view-customer2/view-customer2.component';
import { TransactionHistoryAdminComponent } from './components/transaction-history-admin/transaction-history-admin.component';
import { TransactionHistory2AdminComponent } from './components/transaction-history2-admin/transaction-history2-admin.component';
import { DpsInformationComponent } from './components/dps-information/dps-information.component';



@NgModule({
  declarations: [
    AppComponent,
    AddCustomerComponent,
    ViewCustomerComponent,
    DepositComponent,
    SafePipe,
    WithdrawComponent,
    TransferComponent,
    LoanAplicationFormComponent,
    LoanAplicationListComponent,
    LoanCheckComponent,
    LoanAboutComponent,
    LoanAboutViewComponent,
    TransactionHistoryComponent,
    TransactionHistory2Component,
    LoanPaymentFormComponent,
    HeaderComponent,
    FooterComponent,
    SidebarComponent,
    OkComponent,
    Dashboard1Component,
    Dashboard2Component,
    Header2Component,
    Sidebar2Component,
  
    SearchCustomerComponent,
        DpsAboutViewComponent,
        DpsAboutComponent,
        LoanInformationComponent,
        ReportComponent,
        LoginPageComponent,
        RegistrationPageComponent,
        ViewCustomer2Component,
        TransactionHistoryAdminComponent,
        TransactionHistory2AdminComponent,
        DpsInformationComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

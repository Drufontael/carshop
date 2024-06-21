import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path:'',
    pathMatch:'full',
    redirectTo:'vehicles'
  },
  {
    path:'vehicles',
    loadComponent:() => import('./container/vehicles/vehicles.component').then(m=>m.VehiclesComponent),
  },
  {
    path:'vehicles/new',
    loadComponent: () => import('./container/vehicle-form/vehicle-form.component').then(m=>m.VehicleFormComponent),
  },
  {
    path:'vehicles/:plate',
    loadComponent: () => import('./container/vehicle-form/vehicle-form.component').then(m=>m.VehicleFormComponent),
  },
  {
    path:'consignors',
    loadComponent: () => import('./container/consignors/consignors.component').then(m=>m.ConsignorsComponent),
  },
  {
    path:'consignors/new',
    loadComponent:() => import('./container/consignor-form/consignor-form.component').then(m=>m.ConsignorFormComponent)
  },
  {
    path:'consignors/:id',
    loadComponent:() => import('./container/consignor-form/consignor-form.component')
    .then(m=>m.ConsignorFormComponent)
  },
  {
    path:'consignments',
    loadComponent: ()=>import('./container/consignments/consignments.component')
    .then(m=>m.ConsignmentsComponent)
  },
  {
    path:'consignments/new',
    loadComponent:() => import('./container/consignment-form/consignment-form.component')
    .then(m=>m.ConsignmentFormComponent)
  },
  {
    path:'consignments/:id',
    loadComponent:() => import('./container/consignment-form/consignment-form.component').then(m=>m.ConsignmentFormComponent)
  },
  {
    path:'consignments/pdf/:id',
    loadComponent:()=>import('./container/consignments-pdf/consignments-pdf.component')
    .then(m=>m.ConsignmentsPdfComponent)
  },
  {
    path:'expenses/:plate',
    loadComponent:()=>import('./container/expenses/expenses.component')
    .then(m=>m.ExpensesComponent)
  },
  {
    path:'expenses/:plate/new',
    loadComponent:()=>import('./container/expense-form/expense-form.component')
    .then(m=>m.ExpenseFormComponent)
  },
  {
    path:'expenses/:plate/:id',
    loadComponent:()=>import('./container/expense-form/expense-form.component')
    .then(m=>m.ExpenseFormComponent)
  }



];

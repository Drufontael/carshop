import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatToolbarModule } from '@angular/material/toolbar';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { UUID } from 'crypto';
import { NgxExtendedPdfViewerModule, pdfDefaultOptions } from 'ngx-extended-pdf-viewer';

@Component({
  selector: 'app-consignments-pdf',
  standalone: true,
  imports: [
    CommonModule,
    NgxExtendedPdfViewerModule,
    MatCardModule,
    MatToolbarModule
  ],
  templateUrl: './consignments-pdf.component.html',
  styleUrl: './consignments-pdf.component.scss'
})
export class ConsignmentsPdfComponent {

  pdfSource: any

  constructor(
    private http: HttpClient,
    private sanitizer: DomSanitizer,
    private route: ActivatedRoute) {
    pdfDefaultOptions.assetsFolder = 'bleeding-edge';
  }

  ngOnInit(): void {
    const id = this.route.snapshot.params['id']
    this.downloadPDF(id)
  }

  downloadPDF(id: UUID) {

    const apiUrl = 'api/consignments/pdfContract/' + id;

    return this.http.get(apiUrl, { responseType: 'arraybuffer' })
      .subscribe((data: ArrayBuffer) => {
         this.pdfSource=data //this.sanitizer.bypassSecurityTrustResourceUrl(pdfUrl);
      });
  }

}

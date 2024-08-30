package one.modality.event.frontoffice.activities.booking.process.event.slides;

import dev.webfx.extras.panes.GoldenRatioPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import one.modality.base.frontoffice.utility.StyleUtility;
import one.modality.event.frontoffice.activities.booking.process.event.BookEventActivity;

final class StepALoadingSlide extends StepSlide {

    private static final String LOGO = "M 99.647667,3.2785127 C 99.76505,14.534204 96.658322,29.420816 100.57526,38.656198 104.26509,28.874848 100.63665,14.49856 101.0133,3.1496826 100.48662,-1.4736955 100.05788,-0.64218984 99.647667,3.2785127 Z M 122.68309,13.404825 C 120.10508,21.401199 109.97253,35.812955 114.63744,41.094369 117.76324,33.697913 123.42332,15.808178 122.68309,13.404825 Z M 111.13961,16.445299 C 110.40517,22.217067 103.24632,38.209885 108.33104,38.115093 109.37498,32.03511 112.15213,19.636603 111.13961,16.445299 Z M 78.982773,16.367997 C 79.387505,21.51695 91.156712,49.514231 88.547855,34.707047 85.483488,28.863172 82.361904,20.897439 78.982773,16.367997 Z M 91.015822,19.279632 C 91.862379,23.990595 90.762922,41.216977 95.855735,37.800205 94.626758,33.46616 93.671113,16.270236 91.015822,19.279632 Z M 146.10502,24.278373 C 140.21443,30.956401 123.00855,40.522356 124.39256,46.79042 132.29298,40.089942 144.40984,28.6014 148.13093,22.822555 Z M 53.989074,24.278373 C 60.764153,30.623983 69.400927,47.081702 77.343367,45.648631 70.511352,38.288668 60.812059,29.241264 53.989074,24.278373 Z M 128.60944,25.747082 C 126.5415,29.964767 114.77019,43.204227 119.62329,42.832415 121.38355,39.407811 132.43832,23.445104 128.60944,25.747082 Z M 70.943562,26.494309 C 73.28837,30.236131 79.651325,46.539234 82.564344,41.23286 78.929624,36.319003 75.8905,29.937289 70.943562,26.494309 Z M 138.47808,42.675795 C 130.43686,46.742548 124.91809,52.963039 131.01218,51.867665 128.07307,56.701203 123.33437,66.525065 121.37221,55.578473 111.4277,39.476875 82.967074,42.547567 76.148437,60.222925 69.667088,71.814088 75.419793,87.760081 87.743452,92.714729 84.614685,98.288846 70.77487,104.87145 79.311702,94.216047 76.339374,93.89999 69.669767,94.788124 75.143535,89.236225 71.628158,89.203268 61.299964,89.313804 70.12547,84.917081 69.424811,79.089548 59.240703,90.710595 53.532923,88.661711 40.964035,96.719589 65.574752,85.959274 67.825793,95.111033 78.338648,103.47291 66.692794,111.81561 64.836854,119.22867 70.358681,109.77121 87.044853,110.68819 88.899683,122.34256 88.113087,130.90038 106.86396,125.17797 104.62063,137.78069 105.19698,146.70501 84.314226,137.60195 84.835034,149.71067 93.855721,138.1812 117.16598,151.43052 105.00713,163.31549 116.29647,161.55893 107.27367,141.52157 122.76562,145.76153 132.45835,146.7041 131.86154,153.36343 125.1724,157.97333 121.38329,163.38484 127.07825,171.72641 126.9346,161.09955 132.56398,150.22543 142.73039,157.99404 147.0745,164.53619 144.43315,161.50644 141.21325,144.60045 154.06693,145.43341 159.85269,143.34229 164.56382,160.35433 166.74415,153.65298 153.61218,144.24423 174.31675,125.96803 182.74527,138.55369 188.12443,137.344 173.90211,125.31392 167.54292,131.23595 159.63515,119.22922 177.65187,116.10325 180.14284,116.3428 170.5626,104.09373 189.62645,89.803096 199.90267,100.15487 195.48432,86.131967 177.62917,100.28058 175.58535,85.2907 168.8068,83.178515 151.77583,75.197316 156.92382,69.569792 162.81614,69.651335 180.95167,67.983432 165.23559,67.272935 160.06771,69.62915 144.83309,64.694827 150.79617,59.758313 168.56743,55.957088 129.09132,61.637828 144.68906,52.551693 149.17471,50.381808 164.78293,42.316374 152.46617,46.830675 148.06946,49.212934 131.48063,56.055164 135.01519,47.219253 139.98891,43.519805 150.10357,34.587561 138.47808,42.675795 Z M 106.32124,50.019312 C 120.17649,51.051949 125.39085,71.77204 108.20328,72.562044 98.776938,74.360935 93.289218,79.487624 90.783921,88.643593 76.810264,84.549033 73.937057,65.339943 84.085788,55.884103 89.830326,49.704278 98.369037,48.233278 106.32124,50.019312 Z M 135.30877,55.121118 C 142.62499,62.380337 150.48979,65.307249 153.10431,76.17412 156.59571,85.907053 177.76044,84.327055 174.29379,94.415333 163.67517,98.559768 160.20658,89.348484 150.59487,88.727332 150.70549,93.226021 159.92663,93.057187 155.60972,99.761927 168.98513,99.422032 153.58563,113.17355 149.2228,103.51098 144.47768,105.80304 145.56562,86.291933 142.52345,97.043534 137.40698,109.33667 154.20308,109.80491 160.63744,112.14283 167.34359,119.1854 157.14703,117.75179 154.59877,113.24556 142.74151,111.21587 156.29285,118.19251 152.49838,124.50762 167.20067,131.18532 142.42154,144.97607 136.07373,135.17746 134.32134,130.33596 131.04711,132.56609 131.84638,127.57386 126.34066,128.61233 116.731,128.64229 126.28399,123.47696 123.51523,116.84687 130.86121,111.9778 127.44993,106.39685 131.39994,100.53938 125.89528,91.151566 124.6156,101.99075 122.66678,103.64141 121.01396,118.02391 118.71502,108.35513 118.96131,99.599487 112.414,114.58915 114.34435,116.66489 126.72003,114.85401 111.87793,127.7076 106.7335,121.83111 101.28791,125.42965 87.184167,124.08865 94.030525,116.13666 99.524162,114.77939 96.605666,123.64238 103.53843,118.5072 109.58103,117.7231 114.73138,103.79074 106.347,112.09129 96.349793,121.42705 103.94404,106.11913 110.46968,104.87662 112.05076,97.811524 107.86638,92.448118 115.72609,87.432554 114.29078,82.684032 103.1612,91.29195 106.13121,97.478348 100.18824,105.38816 103.63609,87.399548 99.266804,98.19418 94.672496,103.4765 96.162054,114.37324 85.769079,109.29883 82.644498,104.1203 71.25521,107.39105 83.123814,102.55686 95.251987,99.836351 94.752589,84.017016 102.58507,78.929546 115.9625,76.479595 129.64206,68.444909 132.90925,54.338449 L 133.73701,53.137079 Z M 134.53577,62.361559 C 130.81181,71.156937 146.84181,77.111571 142.29156,67.901394 136.08171,71.340917 139.45437,62.878703 134.53577,62.361559 Z M 146.23386,81.32585 C 150.16337,87.270531 158.64447,86.126776 149.13584,81.915262 L 147.12321,81.360875 Z M 137.62778,85.010486 C 137.95663,93.781859 148.59095,90.609989 137.62778,85.010486 Z M 127.3211,86.504959 C 123.83289,98.406957 133.28631,90.85235 127.3211,86.504959 Z M 149.37739,127.75745 C 143.53803,127.68001 141.28641,132.23845 136.7356,128.49824 137.85521,139.83732 153.48788,130.70823 149.37739,127.75745 Z M 55.50931,40.949426 C 60.096137,43.773725 69.532872,55.357421 73.185266,50.32851 67.682584,47.090944 61.064017,42.479303 55.50931,40.949426 Z M 45.872565,48.318706 C 51.929296,50.475824 66.102704,61.420781 69.526393,56.229081 62.168189,53.311479 52.085297,49.504713 45.872565,48.318706 Z M 47.547399,60.815558 C 51.794074,62.289886 68.103455,69.18121 66.556782,63.537164 60.294972,62.339486 53.94099,60.898982 47.547399,60.815558 Z M 58.240578,69.653538 C 50.636986,70.12635 32.769131,69.941529 30.886006,71.99186 41.844321,71.024591 60.87426,77.423476 67.11439,70.073855 64.350859,68.904526 61.13372,69.863233 58.240578,69.653538 Z M 57.85408,78.285375 C 54.314766,78.871593 40.48735,82.919655 52.170207,81.243164 56.615785,82.271402 76.953663,76.597503 62.856241,77.060204 61.187847,77.464472 59.520542,77.87322 57.85408,78.285375 Z M 0.23973669,101.24352 C 1.1783115,152.92701 36.873749,201.47252 85.754182,218.09247 132.01577,234.87697 187.29147,221.65053 220.68276,185.40892 243.24784,162.08778 255.90856,129.83761 256,97.455809 247.49776,96.247507 254.0847,110.39421 250.6816,115.29411 244.12606,165.55354 203.43235,208.69815 153.84148,218.66582 109.81307,228.36948 61.3476,211.72677 32.876533,176.66199 14.42963,154.73101 3.9902311,126.1318 4.259342,97.455809 0.41541348,97.368579 -0.51130529,96.713622 0.23973669,101.24352 Z M 14.566022,99.104866 C 12.6257,107.2078 22.631566,99.697521 24.971395,104.64035 24.889746,109.56191 12.257556,113.27916 16.596256,120.83017 19.992818,119.12575 24.791378,110.50674 29.278734,109.12941 33.415931,112.97114 44.413336,118.71371 37.421085,109.66922 33.352883,107.17386 21.776901,100.59531 33.887826,102.30317 40.557277,104.30324 39.408945,94.474772 33.213154,97.455809 26.997444,97.455809 20.781733,97.455809 14.566022,97.455809 14.566022,98.005494 14.566022,98.555181 14.566022,99.104866 Z M 48.835732,100.08402 C 49.287646,139.70213 83.730319,175.23422 123.40979,176.59138 162.29408,179.7732 200.12664,149.80301 206.16987,111.29314 209.27423,107.01184 206.90358,90.786865 203.3729,100.34282 202.80058,137.99502 170.0008,171.25091 132.46883,172.69968 96.492076,175.56018 61.423159,148.77244 54.426907,113.4375 53.102827,109.3742 53.817376,91.29857 48.835732,99.623622 Z M 226.08486,97.687705 C 215.33592,101.06172 226.02564,110.99931 219.28246,116.52317 223.52519,122.42844 215.05911,120.36935 219.40526,125.31255 224.31882,128.12111 241.31742,130.76842 231.82359,123.95888 223.40658,125.50642 217.95796,112.57188 229.31385,115.46084 240.80352,119.2521 237.72477,108.10371 228.1788,110.4221 216.64191,104.93842 231.44772,100.97736 237.96331,102.91835 240.33011,94.378044 231.36172,98.659585 226.08486,97.687705 Z M 25.568403,119.64094 C 13.767712,120.50966 19.982778,134.84148 22.192965,136.80156 19.797549,145.56387 32.72278,136.23017 37.846065,134.85273 33.803021,131.16853 38.07472,117.81245 25.568403,119.64094 Z M 30.824811,125.07771 C 40.524849,136.17355 13.237584,136.59163 24.434668,125.26452 26.399329,124.34283 28.832084,123.98105 30.824811,125.07771 Z M 216.37081,129.58688 C 208.64942,138.33781 220.82022,147.66298 225.87873,136.33776 232.4453,136.26312 220.50653,150.43788 228.40386,145.7426 236.47818,138.84942 227.80296,123.54566 220.87999,135.95126 211.85597,142.42495 224.21027,124.40479 216.37081,129.58688 Z M 204.33776,140.09969 C 201.62101,149.58127 214.47382,138.98287 204.33776,140.09969 Z M 34.200248,140.35736 C 21.477657,142.02144 29.132844,155.77571 31.560829,158.08037 35.306084,165.6817 47.662659,149.77341 54.736308,147.03094 53.476237,137.15821 41.614364,157.4006 43.453714,144.44139 41.797265,141.12493 37.730876,139.4352 34.200248,140.35736 Z M 39.353588,145.02114 C 48.694533,155.61218 22.23028,156.85698 33.103151,145.77844 34.907612,144.6092 37.32482,144.06917 39.353588,145.02114 Z M 210.75368,143.1144 C 206.87168,149.18409 220.54015,150.24384 223.79788,154.33316 231.82885,149.50571 213.44787,143.87715 210.75368,143.1144 Z M 205.83224,152.08121 C 202.64372,160.24045 201.76948,159.1139 195.7168,153.43294 189.46084,154.2316 196.3046,159.51995 199.1397,161.60474 203.28252,165.22666 207.41491,168.86049 211.55244,172.48844 220.45017,167.5309 195.25186,158.92285 209.723,156.04928 214.25258,163.40179 227.79297,160.97461 217.24688,155.45665 213.86948,153.50312 210.01602,149.64624 205.83224,152.08121 Z M 45.357228,157.41492 C 35.72969,158.96594 36.136148,174.58656 46.516731,173.57064 42.956474,182.22337 53.538598,175.0025 55.754164,170.08023 61.07965,167.58966 58.163454,162.92645 55.535075,163.80393 55.728933,158.65929 49.763581,155.80785 45.357228,157.41492 Z M 51.360871,161.66642 C 59.556344,171.16176 35.527322,174.80701 44.455395,163.08359 46.233373,161.45801 49.088546,160.33013 51.360871,161.66642 Z M 188.80045,159.65662 C 184.92436,163.10366 200.99954,169.03522 189.84078,169.22251 177.68968,178.96929 202.68952,194.64478 203.6163,177.07491 215.90342,178.48558 196.66407,165.73423 193.87673,161.71262 192.24631,160.95892 190.42153,156.14033 188.80045,159.65662 Z M 197.14886,171.14857 C 209.63041,178.94582 189.12266,186.62677 191.0035,173.22279 191.71029,170.60843 194.9881,169.74116 197.14886,171.14857 Z M 60.894549,169.83447 C 55.160654,171.84521 47.332161,188.63943 56.47189,181.47946 58.492341,173.91826 72.160476,172.83181 64.665145,182.24868 56.525923,188.16687 64.992241,194.72772 68.020652,184.94341 73.978112,174.68183 78.902363,186.42901 72.013283,191.65484 69.265336,200.26833 77.50456,195.30658 78.078925,189.56934 84.505724,182.60475 76.204952,177.15667 71.330063,177.38411 70.960589,170.54152 61.658155,174.84483 63.831955,169.78293 63.399294,168.28686 61.210312,168.28741 60.894549,169.83447 Z M 175.2214,170.4271 C 170.98696,173.65377 184.43086,181.47132 174.08444,179.96078 159.29896,187.34525 182.227,207.3029 185.78574,190.08709 193.12191,196.64738 188.10565,181.89157 183.44097,179.13625 180.68102,177.3879 179.03481,168.08703 175.2214,170.4271 Z M 180.1686,182.76935 C 192.2906,192.82023 168.61799,196.76173 174.8349,183.38775 176.08799,181.64847 178.53329,181.88283 180.1686,182.76935 Z M 83.182746,186.40245 C 81.928534,192.36604 70.170926,205.76757 79.443354,205.63729 81.794746,197.80415 82.376086,197.93919 88.144446,202.83597 104.76727,204.3155 97.563629,180.74914 87.640385,185.47485 89.042737,182.167 82.200239,182.63647 83.182746,186.40245 Z M 92.123791,188.97913 C 101.36621,201.95871 75.417196,200.76904 87.743049,189.04072 89.050292,188.2764 90.814555,188.10478 92.123791,188.97913 Z M 136.08178,184.54725 C 124.39668,184.19766 134.28647,201.51265 131.80451,208.61335 142.92623,212.73101 157.03879,199.27481 143.90661,194.40315 150.9707,188.88897 142.13865,182.6033 136.08178,184.54725 Z M 140.84862,188.05152 C 146.2341,197.31316 128.33661,194.96049 136.49404,187.63926 L 138.93221,187.56517 Z M 142.29156,197.71404 C 150.56645,205.10124 130.82342,209.41622 136.08178,197.68827 138.11531,197.14158 140.26359,197.22559 142.29156,197.71404 Z M 162.31228,185.52639 C 157.57176,188.04683 170.53773,200.70896 159.58221,200.7235 154.84512,199.14397 155.25583,182.14489 150.28889,190.9245 149.3385,202.4774 161.00615,208.05353 166.71838,200.78028 175.98266,206.2053 164.76294,182.93171 162.31228,185.52639 Z M 106.37278,190.3963 C 94.428505,194.59582 102.96944,214.38185 113.69374,205.44083 115.26141,212.71903 120.33107,205.06882 118.64918,200.32695 121.68134,185.88338 115.10768,194.02334 106.37278,190.3963 Z M 112.65985,193.97787 C 120.67965,202.04112 102.61322,209.85163 105.44195,197.32754 106.0924,194.0082 109.71601,192.30693 112.65985,193.97787 Z M 160.92087,232.88558 C 155.55539,233.1917 159.59382,241.59877 158.34487,245.4492 151.37652,262.62363 183.82466,252.41658 169.2156,243.04947 177.40672,237.42259 168.37316,230.94612 160.92087,232.88558 Z M 166.30611,235.82299 C 173.84573,241.28114 157.40659,245.15134 162.38957,235.74569 L 164.07729,235.58143 Z M 167.31101,245.20206 C 173.26245,252.83149 158.03579,254.43079 162.20922,245.27482 161.85472,243.36463 166.49557,245.30024 167.31101,245.20206 Z M 62.749753,243.81067 C 63.812005,247.20643 60.255169,255.22128 65.301793,254.63268 68.595114,252.57102 63.768219,235.60153 67.542359,239.99719 71.611692,243.93613 72.304041,254.25205 78.571642,254.63268 81.282247,252.8595 79.006098,246.09957 79.755774,242.36546 78.956916,238.90498 82.127958,231.4746 76.354569,232.98865 76.428311,238.48539 76.488389,243.98229 76.534935,249.47933 73.427169,244.02754 70.781235,237.06404 66.536369,233.0061 60.560564,231.14329 63.338925,238.10727 62.749753,241.65991 62.749753,242.37682 62.749753,243.09374 62.749753,243.81067 Z M 84.136114,243.81067 C 85.203431,247.23572 81.59732,255.3342 86.791222,254.63268 90.035897,253.50823 85.789601,246.59267 89.92487,244.54634 91.654145,248.17446 98.604891,258.38286 99.750476,253.28291 93.883825,246.36093 91.802267,239.02745 99.361013,233.05628 93.19548,230.37619 90.72136,242.41685 88.001117,242.41813 86.95443,239.43662 90.5105,231.85235 85.346013,232.98865 82.572132,233.92671 84.905577,240.77107 84.136114,243.81067 Z M 102.43047,234.53465 C 102.78023,238.20677 111.52286,233.48601 108.61448,240.41627 108.61448,245.15508 108.61448,249.89388 108.61448,254.63268 114.81521,256.45307 111.79529,249.05282 112.47948,245.35667 113.12447,241.59518 110.18269,234.24691 116.42292,236.08065 122.69633,235.45143 116.34119,231.07148 112.41771,232.98865 109.90192,233.81317 102.11196,231.18017 102.43047,234.53465 Z M 129.74317,243.81067 C 130.81048,247.23572 127.20437,255.3342 132.39827,254.63268 135.159,252.90104 132.84856,246.09822 133.60817,242.36546 132.92266,238.63703 135.93851,231.20724 129.74317,232.98865 129.74317,236.59599 129.74317,240.20332 129.74317,243.81067 Z M 137.98851,243.81067 C 139.05583,247.23572 135.44972,255.3342 140.64362,254.63268 143.66885,254.23957 139.71014,242.26456 144.94875,246.23917 145.28527,251.40126 157.29114,259.97184 151.84456,250.34212 142.67152,244.63559 151.45006,236.98437 153.26703,232.98865 147.04606,231.48707 145.64195,239.80353 141.98235,243.14073 140.82953,240.20579 144.13205,232.80256 139.92101,232.98865 135.89642,232.3004 138.93888,241.15169 137.98851,243.81067 Z M 176.6901,240.89902 C 170.97551,254.8469 194.32668,260.96225 193.26332,244.91823 191.91376,241.07289 196.72376,231.10728 189.54767,232.98865 189.35952,239.72517 191.46479,256.91475 181.04467,249.37627 176.99926,245.26459 184.14275,232.47002 176.84243,232.98865 176.34304,235.10907 176.80802,238.40391 176.6901,240.89902 Z M 117.11748,246.00083 C 117.02783,248.72745 128.55342,248.15919 124.38256,244.58367 122.42493,245.32443 116.66788,242.96979 117.11748,246.00083 Z";

    StepALoadingSlide(BookEventActivity bookEventActivity) {
        super(bookEventActivity);
    }

    @Override
    void buildSlideUi() {
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(LOGO);
        svgPath.setFill(Color.WHITE);
        mainVbox.setAlignment(Pos.CENTER);
        GoldenRatioPane goldenRatioPane = new GoldenRatioPane(svgPath);
        VBox.setVgrow(goldenRatioPane, Priority.ALWAYS);
        mainVbox.getChildren().setAll(goldenRatioPane);
        mainVbox.setBackground(Background.fill(StyleUtility.MAIN_ORANGE_COLOR));
        mainVbox.setPadding(Insets.EMPTY); // Removing extra bottom padding
    }
}
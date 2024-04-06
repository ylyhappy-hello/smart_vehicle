{
  inputs.nixpkgs.url = "nixpkgs/nixos-23.05";
  outputs =
    { self
    , nixpkgs
    , flake-utils
    ,
    }:
    flake-utils.lib.eachDefaultSystem
      (system:
      let
        overlays = [
        (self: super: {
          pnpm = super.nodePackages.pnpm.override {
            nodejs = pkgs.nodejs-16_x;
          };

        })
        ];
        pkgs = import nixpkgs {
          inherit system overlays;
          config = {
            allowUnfree = true;
            permittedInsecurePackages = [
              "nodejs-16.20.1"
            ];
          };
        };
      in
      {
        devShells.default = pkgs.mkShell {
          name = "vite";
          buildInputs = with pkgs; [
            pkgs.nodejs-16_x
            pnpm
          ];
          shellHook = ''pnpm i'';
        };
      }
      );
}
